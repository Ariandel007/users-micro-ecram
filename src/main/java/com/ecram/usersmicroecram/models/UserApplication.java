package com.ecram.usersmicroecram.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "user_app")
public class UserApplication implements Serializable {
    private static final long serialVersionUID = 4002221912401133094L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, unique = true)
    private String username;

    @Column(length = 255)
    private String password;

    @Column(length = 255)
    private String email;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked = false;

    @Column(name = "auth_type")
    private String authType = "email_registered";

    @Column(nullable = false)
    private short attemps = 0;

    @Column(length = 255, nullable = false)
    private String firstname;

    @Column(length = 255, nullable = false)
    private String lastname;

    @Column(length = 255, nullable = false)
    private String country;

    @Column(length = 255, nullable = false)
    private String city;

    @Column(length = 255)
    private String age;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Column(name = "birth_date_utc", length = 255)
    private String birthDateUtc;

    @Column(name = "account_creation_date")
    private Instant accountCreationDate;

    @Column(name = "account_creation_date_utc", length = 255)
    private String accountCreationDateUtc;


    @OneToMany(mappedBy = "userApplication", fetch = FetchType.LAZY)
    private List<UserRol> userRolList;

    @OneToMany(mappedBy = "userApplication", fetch = FetchType.LAZY)
    private List<Email> emailList;

}
