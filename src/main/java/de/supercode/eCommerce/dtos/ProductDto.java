package de.supercode.eCommerce.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private BigDecimal price;
    @Min(value = 0, message = "Stock quantity must be at least 0")
    private int stockQuantity;
    private String category;
    private String imageUrl;
}
