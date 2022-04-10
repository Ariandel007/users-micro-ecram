package com.ecram.usersmicroecram.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "rol_app")
public class RolApp implements Serializable {
    private static final long serialVersionUID = 4002221912401133094L;

    @Id
    private Long id;

    @Column(length = 255)
    private String name;

    @OneToMany(mappedBy = "rolApp", fetch = FetchType.LAZY)
    private List<UserRol> userRolList;

}
