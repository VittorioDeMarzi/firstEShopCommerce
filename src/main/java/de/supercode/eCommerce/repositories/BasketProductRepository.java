package de.supercode.eCommerce.repositories;

import de.supercode.eCommerce.entities.customer.Customer;
import de.supercode.eCommerce.entities.orders.Basket;
import de.supercode.eCommerce.entities.orders.BasketProduct;
import de.supercode.eCommerce.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {

    Optional<BasketProduct> findByBasketAndProduct(Basket basket, Product product);
}
