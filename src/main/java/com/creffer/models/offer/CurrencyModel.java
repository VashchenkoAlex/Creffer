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
@Table(name = "currencies")
public class CurrencyModel {
    @Column(name = "currency_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int currencyId;

    @Column(name = "currency_name")
    private String currencyName;
}
