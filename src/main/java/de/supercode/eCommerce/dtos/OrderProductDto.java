package de.supercode.eCommerce.dtos;

import de.supercode.eCommerce.entities.product.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProductDto {
    private String productName;
    private int quantity;
    private BigDecimal price;


}
