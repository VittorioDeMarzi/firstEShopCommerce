package de.supercode.eCommerce.controllers;

import de.supercode.eCommerce.dtos.ProductDto;
import de.supercode.eCommerce.entities.product.Product;
import de.supercode.eCommerce.errors.ApiError;
import de.supercode.eCommerce.servicies.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // save new Product
    @PostMapping
    public ResponseEntity<?> saveNewProduct(@RequestBody ProductDto productDto) {
        try {
            Product newProduct = productService.saveNewProduct(productDto);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Article already in DataBase"), HttpStatus.BAD_REQUEST);
        }
    }

    // find all products
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            productService.findAllProducts();
            return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // find product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<?> findById(@PathVariable long productId) {
        Optional<Product> productById = productService.findById(productId);
        if (productById.isPresent()) return ResponseEntity.status(HttpStatus.FOUND).body(productById.get());
        else return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, "Product with ID: " + productId + " not found."), HttpStatus.NOT_FOUND);
    }

    // delete Product By ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable long productId) {
        try {
            Product eventToDelete = productService.deleteProductById(productId).get();
            return ResponseEntity.status(HttpStatus.GONE).body(eventToDelete);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, "Product with ID: " + productId + " not found."), HttpStatus.NOT_FOUND);
        }
    }
}
