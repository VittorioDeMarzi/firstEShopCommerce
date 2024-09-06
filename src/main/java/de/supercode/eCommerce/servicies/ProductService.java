package de.supercode.eCommerce.servicies;

import de.supercode.eCommerce.dtos.ProductDto;
import de.supercode.eCommerce.entities.product.Product;
import de.supercode.eCommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // save new product
    public Product saveNewProduct(ProductDto productDto) {
        Product newProduct = getProductFromDto(productDto);
        productRepository.save(newProduct);
        return newProduct;
    }

    private Product getProductFromDto(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setModell(productDto.getModell());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setCategory(productDto.getCategory());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }

    // find all products
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // find product by ID
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    // delete Product By ID
    public Optional<Product> deleteProductById(long id) {
        Optional<Product> eventToDelete = findById(id);
        productRepository.deleteById(id);
        return eventToDelete;
    }
}
