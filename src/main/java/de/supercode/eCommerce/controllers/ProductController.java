package de.supercode.eCommerce.controllers;

import de.supercode.eCommerce.dtos.ProductDto;
import de.supercode.eCommerce.entities.product.Product;
import de.supercode.eCommerce.errors.ApiError;
import de.supercode.eCommerce.servicies.ProductService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<?> saveNewProduct(@Validated @RequestBody ProductDto productDto) {
        try {
            Product newProduct = productService.saveNewProduct(productDto);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Article already in DataBase"), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationError(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errorMap.put(fieldName, message);
        });

        return errorMap;
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
