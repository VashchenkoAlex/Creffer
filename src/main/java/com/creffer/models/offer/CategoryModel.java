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
@Table(name = "categories")
public class CategoryModel {
    @Column(name = "cat_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int catId;

    @Column(name = "cat_name")
    private String catName;

}
