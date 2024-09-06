package de.supercode.eCommerce.repositories;

import de.supercode.eCommerce.entities.orders.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByCustomerId(Long customerId);

}
