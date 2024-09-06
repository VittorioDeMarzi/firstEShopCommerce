package de.supercode.eCommerce.dtos;

import de.supercode.eCommerce.entities.product.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasketProductResponseDto {
    private Product product;

    private int quantity;

    private BigDecimal basketProductTotalPrice;
}
