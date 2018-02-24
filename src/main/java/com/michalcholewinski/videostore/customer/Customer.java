package com.michalcholewinski.videostore.customer;

import com.michalcholewinski.videostore.rental.Rental;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "customer")
    private Set<Rental> rentals;

}
