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
@Table(name = "connections")
public class ConnectionTypeModel {
    @Column(name = "conn_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int connId;

    @Column(name = "conn_name")
    private String connName;
}
