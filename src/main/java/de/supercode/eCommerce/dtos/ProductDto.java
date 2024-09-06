package de.supercode.eCommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private String brand;
    @NotBlank
    private String modell;
    private String description;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private int stockQuantity;
    private String category;
    private String imageUrl;
}
