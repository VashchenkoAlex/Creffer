package com.creffer.models.users;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "managers")
public class ManagerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "manager_id")
    private int id;

    @Column(name = "email")
    @Email(message = "*Please privide a valid Email*")
    @NotEmpty(message = "*Please provide an email*")
    private String email;

    @Column(name = "skype")
    private String skype;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "active")
    private int active;

    @Column(name = "notes")
    private String notes;

    @Column(name = "password")
    private String password;

}
