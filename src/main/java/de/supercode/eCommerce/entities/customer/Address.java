package de.supercode.eCommerce.entities.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    @JsonBackReference
    @OneToMany(mappedBy = "address")
    private Set<Customer> customers;

}
