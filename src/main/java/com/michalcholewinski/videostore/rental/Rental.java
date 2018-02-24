package com.michalcholewinski.videostore.rental;

import com.michalcholewinski.videostore.customer.Customer;
import com.michalcholewinski.videostore.video.VideoItem;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Rental {

    @Id
    @GeneratedValue
    private Long id;

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
