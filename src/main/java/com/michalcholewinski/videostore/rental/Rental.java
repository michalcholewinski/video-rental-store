package com.michalcholewinski.videostore.rental;

import com.michalcholewinski.videostore.customer.Customer;
import com.michalcholewinski.videostore.video.VideoItem;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class Rental {

    @Id
    @GeneratedValue
    private Long id;

    @GenericGenerator(
            name = "rental-sequence",
            strategy = "com.michalcholewinski.videostore.rental.RentalUUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence_name", value = "hibernate_sequence")
            }
    )
    @GeneratedValue(
            generator = "rental-sequence",
            strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private String uuid;


    @ManyToMany(targetEntity = VideoItem.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "RENTAL_VIDEOITEM",
            joinColumns = @JoinColumn(name = "RENTAL_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "VIDEO_ITEM_ID", referencedColumnName = "id")
    )
    private Set<VideoItem> videoItems;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;
}
