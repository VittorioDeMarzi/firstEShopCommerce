package de.supercode.eCommerce.controllers;

import de.supercode.eCommerce.repositories.OrderRepository;
import de.supercode.eCommerce.servicies.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ecommerce/order")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/make-order/{customerId}")
    public ResponseEntity<?> makeOrder(@PathVariable long customerId) {
        try {
            return ResponseEntity.ok(orderService.makeAnOrder(customerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

//    @GetMapping("/{customerId}")
//    public
}
