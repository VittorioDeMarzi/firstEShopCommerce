package de.supercode.eCommerce.entities.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.supercode.eCommerce.entities.orders.OrderProduct;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String modell;
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int stockQuantity;
    private String category;
    private String imageUrl;
    @CreationTimestamp
    private LocalDate createdDate;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties
    private Set<OrderProduct> oderProducts = new HashSet<OrderProduct>();

}
