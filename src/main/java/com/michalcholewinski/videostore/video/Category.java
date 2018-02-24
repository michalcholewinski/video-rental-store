package com.michalcholewinski.videostore.video;

import com.michalcholewinski.videostore.pricing.Tariff;
import com.michalcholewinski.videostore.rental.Rental;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Tariff> tariffs;

}
