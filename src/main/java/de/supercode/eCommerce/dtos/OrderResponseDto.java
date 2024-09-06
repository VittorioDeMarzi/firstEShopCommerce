package de.supercode.eCommerce.dtos;

import de.supercode.eCommerce.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderResponseDto {
    private String firstName;
    private String lastName;
    private LocalDate OrderDate;
    private OrderStatus orderStatus;
    private Set<OrderProductDto> orderProductDtoSet = new HashSet<>();
    private BigDecimal totalPrice;
}
