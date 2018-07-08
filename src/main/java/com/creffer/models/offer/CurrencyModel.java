package com.creffer.models.offer;

import javax.persistence.*;

@Entity
@Table(name = "currencies")
public class CurrencyModel {
    @Column(name = "currency_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int currencyId;

    @Column(name = "currency_name")
    private String currencyName;
}
