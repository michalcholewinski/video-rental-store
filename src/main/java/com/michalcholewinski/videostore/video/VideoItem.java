package com.michalcholewinski.videostore.video;

import com.michalcholewinski.videostore.rental.Rental;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class VideoItem {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private VideoItemStatus status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Video video;

    @ManyToMany(mappedBy = "videoItems")
    private Set<Rental> rentals;

}
