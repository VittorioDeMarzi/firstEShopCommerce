package de.supercode.eCommerce.entities.orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.supercode.eCommerce.entities.product.Product;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Setter
@Getter
public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "basketId")
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "itemId", nullable = false)
    private Product product;

    private int quantity;

    private BigDecimal price;


}
