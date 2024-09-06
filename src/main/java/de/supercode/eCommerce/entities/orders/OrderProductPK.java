package de.supercode.eCommerce.entities.orders;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
public class OrderProductPK implements Serializable {

    private Long orderId;
    private Long productId;

    public OrderProductPK(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderProductPK() {
    }


}
