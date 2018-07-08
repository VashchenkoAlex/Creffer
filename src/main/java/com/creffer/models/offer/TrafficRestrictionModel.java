package com.creffer.models.offer;

import javax.persistence.*;

@Entity
@Table(name = "traffic_restrictions")
public class TrafficRestrictionModel {
    @Column(name = "restr_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int restrId;

    @Column(name = "restr_name")
    private String restrName;
}
