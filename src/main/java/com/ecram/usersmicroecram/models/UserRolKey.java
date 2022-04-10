package com.ecram.usersmicroecram.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserRolKey implements Serializable {

    @Column(name = "id_user_app", nullable = false)
    private Long userAppId;

    @Column(name = "id_rol_app", nullable = false)
    private Long rolAppId;

}
