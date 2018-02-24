package com.michalcholewinski.videostore.video;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private Set<VideoItem> user;

}
