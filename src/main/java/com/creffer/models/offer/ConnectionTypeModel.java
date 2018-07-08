package com.creffer.models.offer;

import javax.persistence.*;

@Entity
@Table(name = "connections")
public class ConnectionTypeModel {
    @Column(name = "conn_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int connId;

    @Column(name = "conn_name")
    private String connName;
}
