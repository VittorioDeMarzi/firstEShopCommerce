package de.supercode.eCommerce.servicies;

import de.supercode.eCommerce.dtos.BasketProductResponseDto;
import de.supercode.eCommerce.dtos.BasketResponseDto;
import de.supercode.eCommerce.dtos.addProductToBasketDto;
import de.supercode.eCommerce.entities.customer.Customer;
import de.supercode.eCommerce.entities.orders.Basket;
import de.supercode.eCommerce.entities.orders.BasketProduct;
import de.supercode.eCommerce.entities.product.Product;
import de.supercode.eCommerce.repositories.BasketProductRepository;
import de.supercode.eCommerce.repositories.BasketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BasketService {
    BasketRepository basketRepository;
    CustomerService customerService;
    ProductService productService;
    BasketProductRepository basketProductRepository;

    public BasketService(BasketRepository basketRepository, CustomerService customerService, ProductService productService, BasketProductRepository basketProductRepository) {
        this.basketRepository = basketRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.basketProductRepository = basketProductRepository;
    }

    public void addProductToBasket(addProductToBasketDto dto) {
        Customer customer = customerService.findCustomerById(dto.getCustomerId()).orElseThrow();
        Product product = productService.findById(dto.getProductId()).orElseThrow();


        // check if basket exists for customer, if not create a new one
        Basket basket = createNewBasketForCustomer(customer);
        customerService.customerRepository.save(customer);
        // check if product is already in basket, if so increase quantity
        Optional<BasketProduct> basketProductOptional = basketProductRepository.findByBasketAndProduct(basket, product);
        updateBasketProduct(basket, basketProductOptional, dto, product);
        // save customer
        customerService.customerRepository.save(customer);
    }

    private void updateBasketProduct(Basket basket, Optional<BasketProduct> basketProductOptional, addProductToBasketDto dto, Product product) {
        BasketProduct basketProduct;
        if (basketProductOptional.isEmpty()) {
            basketProduct = new BasketProduct();
            basketProduct.setBasket(basket);
            int quantity = dto.getQuantity();
            basketProduct.setQuantity(quantity);
            basketProduct.setProduct(product);
            BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            basketProduct.setPrice(price);
        } else {
            basketProduct = basketProductOptional.get();
            int quantity = dto.getQuantity();
            basketProduct.setQuantity(basketProduct.getQuantity() + quantity);
            BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            basketProduct.setPrice(basketProduct.getPrice().add(price));
        }
        basketProductRepository.save(basketProduct);
    }

    private Basket createNewBasketForCustomer(Customer customer) {
        Basket basket = customer.getBasket();
        if (basket == null) {
            basket = new Basket();
            basket.setCustomer(customer);
            customer.setBasket(basket);
//            basketRepository.save(basket);
        }
        return basket;
    }

    public void saveNewBasket(Basket basket) {
        basketRepository.save(basket);
    }

    public Optional<BasketResponseDto> findBasketByCustomerId(Long customerId) {
        Basket basket = basketRepository.findById(customerId).orElseThrow();
        return basketToDto(basket);
    }

    private Optional<BasketResponseDto> basketToDto(Basket basket) {
        BasketResponseDto basketResponseDto = new BasketResponseDto();
        basketResponseDto.setCustomerId(basket.getCustomer().getId());
        basketResponseDto.setProducts(basketProductToDtoSet(basket.getBasketProduct()));
        BigDecimal totalPrice = getBasketTotalPrice(basket, basketResponseDto);
        return Optional.of(basketResponseDto);
    }

    private BigDecimal getBasketTotalPrice(Basket basket, BasketResponseDto basketResponseDto) {
        BigDecimal totalPrice =  basket.getBasketProduct().stream().map(BasketProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        basketResponseDto.setTotalPrice(totalPrice);
        return totalPrice;
    }

    private Set<BasketProductResponseDto> basketProductToDtoSet(Set<BasketProduct> basketProduct) {
        Set<BasketProductResponseDto> dtoSet = new HashSet<BasketProductResponseDto>();
        for (BasketProduct bp : basketProduct) {
            BasketProductResponseDto dto = new BasketProductResponseDto();
            dto.setProduct(bp.getProduct());
            dto.setQuantity(bp.getQuantity());
            dto.setBasketProductTotalPrice(bp.getPrice());
            dtoSet.add(dto);
        }
        return dtoSet;
    }

    public void deleteBasket(long id) {
        basketRepository.deleteById(id);
    }
}
