package com.ecram.usersmicroecram.repositories;

import com.ecram.usersmicroecram.models.UserRol;
import com.ecram.usersmicroecram.models.UserRolKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRolRepository  extends JpaRepository<UserRol, UserRolKey> {

    @Query("SELECT DISTINCT ur FROM UserRol ur JOIN FETCH ur.rolApp WHERE ur.isDelete = false AND ur.id.userAppId = :userId ")
    List<UserRol> userRolByUserId(@Param("userId") Long userId);
}
