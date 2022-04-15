package com.ecram.usersmicroecram.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "user_app_rol_app")
public class UserRol implements Serializable {
    private static final long serialVersionUID = 4002221912401133094L;

    @EmbeddedId
    private UserRolKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userAppId")
    @JoinColumn(name = "id_user_app")
    private UserApplication userApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rolAppId")
    @JoinColumn(name = "id_rol_app")
    private RolApp rolApp;

    @Column(name = "is_deleted")
    private boolean isDelete = false;

}
