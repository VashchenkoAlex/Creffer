package com.creffer.models.offer;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryModel {
    @Column(name = "cat_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int catId;

    @Column(name = "cat_name")
    private String catName;
}
