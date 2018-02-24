package com.michalcholewinski.videostore.pricing;

import com.michalcholewinski.videostore.video.Category;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Data
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"currencyCode", "category", "type"})
})
public class Tariff {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @ManyToOne
    private Category category;

    @NotEmpty
    private int amount; //price stored in smallest unit

    @NotEmpty
    @Column(length = 3)
    private String currencyCode;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private TariffType type;


}
