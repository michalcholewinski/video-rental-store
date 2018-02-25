package com.michalcholewinski.videostore.video;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private Set<VideoItem> user;

}
