package de.supercode.eCommerce.entities.orders;

import de.supercode.eCommerce.entities.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class OrderProduct {

    @EmbeddedId
    private OrderProductPK pk = new OrderProductPK();
    @ManyToOne(optional = false)
    @MapsId("orderId")
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;
    @ManyToOne(optional = false)
    @MapsId("productId")
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    private int quantity;
    BigDecimal price;

    public OrderProduct(Order order, Product product, int quantity, BigDecimal price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderProduct() {
    }
}
