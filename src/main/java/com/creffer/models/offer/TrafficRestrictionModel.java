package com.creffer.models.offer;

import com.creffer.models.offer.enums.RestrStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "traffic_restrictions")
public class TrafficRestrictionModel {
    @Column(name = "restr_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int restrId;

    @Column(name = "restr_name")
    private String restrName;

    @Column(name = "restr_status")
    private RestrStatus restrStatus;

}
