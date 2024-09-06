package de.supercode.eCommerce.servicies;

import de.supercode.eCommerce.dtos.CustomerDto;
import de.supercode.eCommerce.entities.customer.Address;
import de.supercode.eCommerce.entities.customer.Customer;
import de.supercode.eCommerce.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;
    AddressService addressService;

    public CustomerService(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    // save new Customer
    public void saveNewCustomer(CustomerDto customerDto) {
        Customer customer = getCustomerFromDto(customerDto);
        customerRepository.save(customer);
    }

    private Customer getCustomerFromDto(CustomerDto customerDto) {
        Address address = getAddressFromDto(customerDto);
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setBirthday(customerDto.getBirthday());
        customer.setAddress(address);
        addressService.addressRepository.save(address);
        return customer;
    }

    private Address getAddressFromDto(CustomerDto customerDto) {
        Address address = new Address();
        address.setStreet(customerDto.getStreet());
        address.setHouseNumber(customerDto.getHouseNumber());
        address.setCity(customerDto.getCity());
        address.setZipCode(customerDto.getZipCode());
        return address;
    }

    // find all customers
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    // find customer by id
        public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }
}
