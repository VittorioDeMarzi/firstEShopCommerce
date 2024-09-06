package de.supercode.eCommerce.entities.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.supercode.eCommerce.entities.orders.Basket;
import de.supercode.eCommerce.entities.orders.Order;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "addressId")
    private Address address;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    @OneToOne(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Basket basket;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

}
