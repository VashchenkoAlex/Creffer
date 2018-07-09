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
@Table(name = "platforms")
public class PlatformModel {
    @Column(name = "platform_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int platformId;

    @Column(name = "platform_name")
    private String platformName;
}
