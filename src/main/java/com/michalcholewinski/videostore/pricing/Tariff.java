package com.michalcholewinski.videostore.pricing;

import com.michalcholewinski.videostore.video.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"currencyCode", "category_id", "type"})
})
public class Tariff {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    private int amount; //price stored in smallest unit

    @NotNull
    @Column(length = 3)
    private String currencyCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TariffType type;


}
