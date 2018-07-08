package com.creffer.models.offer;

import javax.persistence.*;

@Entity
@Table(name = "platforms")
public class PlatformModel {
    @Column(name = "platform_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int platformId;

    @Column(name = "platform_name")
    private String platformName;
}
