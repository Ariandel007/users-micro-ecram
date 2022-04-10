package com.ecram.usersmicroecram.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_email")
public class Email implements Serializable {
    private static final long serialVersionUID = 4002221912401133094L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user_app", nullable = false)
    private Long idUserApp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_app", insertable=false, updatable=false)//insertable=false, updatable=false porque idUserApp esta siendo usado como el FK
    private UserApplication userApplication;
}
