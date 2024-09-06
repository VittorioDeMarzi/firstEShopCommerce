package de.supercode.eCommerce.repositories;

import de.supercode.eCommerce.entities.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
