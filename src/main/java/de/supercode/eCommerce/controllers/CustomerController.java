package de.supercode.eCommerce.controllers;

import de.supercode.eCommerce.dtos.CustomerDto;
import de.supercode.eCommerce.entities.customer.Customer;
import de.supercode.eCommerce.errors.ApiError;
import de.supercode.eCommerce.servicies.CustomerService;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // save new Customer
    @PostMapping
    public ResponseEntity<?> saveNewCustomer(@Validated @RequestBody CustomerDto customerDto) {
        try {
            customerService.saveNewCustomer(customerDto);
            return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Customer with Email: " + customerDto.getEmail() + " already in DataBase"), HttpStatus.BAD_REQUEST);
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

    // find all customers

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            customerService.findAllCustomers();
            return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
