package de.supercode.eCommerce.repositories;

import de.supercode.eCommerce.entities.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
