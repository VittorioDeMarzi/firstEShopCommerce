package de.supercode.eCommerce.servicies;

import de.supercode.eCommerce.dtos.OrderProductDto;
import de.supercode.eCommerce.dtos.OrderResponseDto;
import de.supercode.eCommerce.entities.customer.Customer;
import de.supercode.eCommerce.entities.enums.OrderStatus;
import de.supercode.eCommerce.entities.orders.*;
import de.supercode.eCommerce.entities.product.Product;
import de.supercode.eCommerce.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class OrderService {
    OrderRepository orderRepository;
    CustomerService customerService;
    BasketService basketService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, BasketService basketService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.basketService = basketService;
    }

    @Transactional
    public OrderResponseDto makeAnOrder(long customerId)  {
        Customer customer = customerService.findCustomerById(customerId).orElseThrow();
        Basket basket = customer.getBasket();
        Order newOrder = setTheNewOrder(customer, basket);

        orderRepository.save(newOrder);
        OrderResponseDto orderResponseDto = orderToDto(newOrder);
        customerService.customerRepository.save(customer);
        customer.setBasket(null);
        basketService.deleteBasket(basket.getId());
        return orderResponseDto;
    }

    private Order setTheNewOrder(Customer customer, Basket basket) {
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setStatus(OrderStatus.PROCESSING);
        customer.getOrders().add(newOrder);
        basket.getBasketProduct().forEach(basketProduct -> {
            int quantity = basketProduct.getQuantity();
            BigDecimal price = basketProduct.getPrice();
            Product product = basketProduct.getProduct();
            OrderProduct orderProduct = new OrderProduct(newOrder, product, quantity, price);
            newOrder.getOrderProducts().add(orderProduct);
        });
        BigDecimal totalPrice = basket.getBasketProduct().stream()
                .map(BasketProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        newOrder.setTotalPrice(totalPrice);
        return newOrder;
    }

    private OrderResponseDto orderToDto(Order newOrder) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setFirstName(newOrder.getCustomer().getFirstName());
        orderResponseDto.setLastName(newOrder.getCustomer().getLastName());
        orderResponseDto.setOrderDate(newOrder.getOrderDate());
        orderResponseDto.setOrderStatus(newOrder.getStatus());
        orderResponseDto.setTotalPrice(newOrder.getTotalPrice());

        newOrder.getOrderProducts().forEach(orderProduct -> {
            OrderProductDto orderProductDto = new OrderProductDto();
            orderProductDto.setProductName(orderProduct.getProduct().getName());
            orderProductDto.setQuantity(orderProduct.getQuantity());
            orderProductDto.setPrice(orderProduct.getPrice());
            orderResponseDto.getOrderProductDtoSet().add(orderProductDto);
        });
        return orderResponseDto;
    }
}
