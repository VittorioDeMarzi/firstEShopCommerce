package de.supercode.eCommerce.dtos;

import de.supercode.eCommerce.entities.orders.BasketProduct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class BasketResponseDto {
    private long customerId;
    private Set<BasketProductResponseDto> products;
    private BigDecimal totalPrice;
}
