package com.creffer.models.offer;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "goals")
public class GoalModel {
    @Column(name = "goal_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int goalId;

    @Column(name = "goal_name")
    private String goalName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "goal_countries", joinColumns = @JoinColumn(name = "goal_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<CountryModel> countries;

    @Column(name = "city")
    private String city;

    @Column(name = "payout")
    private float payout;

    @Column(name = "pay_percent")
    private int payPercent;

    @Column(name = "pub_payout")
    private float pubPayout;

    @Column(name = "daily_cap")
    private int dailyCap;

    @Column(name = "monthly_cap")
    private int monthlyCap;

    @Column(name = "total_cap")
    private int totalCap;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "goal_platforms", joinColumns = @JoinColumn(name = "goal_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id"))
    private List<PlatformModel> platforms;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "goal_connections", joinColumns = @JoinColumn(name = "goal_id"),
            inverseJoinColumns = @JoinColumn(name = "conn_id"))
    private List<ConnectionTypeModel> connections;

}
