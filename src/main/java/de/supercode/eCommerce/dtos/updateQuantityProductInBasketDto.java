package de.supercode.eCommerce.dtos;

import lombok.Data;

@Data
public class updateQuantityProductInBasketDto {

    private long customerId;
    private long productId;
    private int newQuantity;
}
