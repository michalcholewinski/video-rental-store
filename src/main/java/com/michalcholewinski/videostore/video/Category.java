package com.michalcholewinski.videostore.video;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.michalcholewinski.videostore.pricing.Tariff;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"tariffs"})
@ToString(exclude = {"tariffs"})
@Accessors(chain = true)
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Tariff> tariffs;

}
