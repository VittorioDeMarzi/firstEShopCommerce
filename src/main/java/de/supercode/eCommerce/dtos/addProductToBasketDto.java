package de.supercode.eCommerce.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class addProductToBasketDto {

    @NotEmpty
    private long customerId;
    @NotEmpty
    private long productId;
    @NotEmpty
    private int quantity;
}
