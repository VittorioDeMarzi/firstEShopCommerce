package de.supercode.eCommerce.servicies;

import de.supercode.eCommerce.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


}
