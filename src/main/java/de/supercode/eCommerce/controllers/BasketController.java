package de.supercode.eCommerce.controllers;

import de.supercode.eCommerce.dtos.addProductToBasketDto;
import de.supercode.eCommerce.servicies.BasketService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ecommerce/basket")
public class BasketController {
    BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    // Add, get, update, delete methods for basket
    @PostMapping
    public ResponseEntity<?> addProductToBasket(@RequestBody addProductToBasketDto dto) {
        try {
            basketService.addProductToBasket(dto);
            return ResponseEntity.ok("Product added to basket successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getBasketByCustomerId(@PathVariable long customerId) {
        try {
            basketService.findBasketByCustomerId(customerId);
            return new ResponseEntity<>(basketService.findBasketByCustomerId(customerId).get(), HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }
}
