package com.creffer.models.offer;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "countries")
public class CountryModel {
    @Column(name = "country_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "two_lit_country")
    private String twoLitCountry;

    @Column(name = "tree_lit_country")
    private String treeLitCountry;

}
