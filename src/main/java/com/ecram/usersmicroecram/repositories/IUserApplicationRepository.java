package com.ecram.usersmicroecram.repositories;

import com.ecram.usersmicroecram.dtos.projections.IUserApplicationListProjection;
import com.ecram.usersmicroecram.models.UserApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface IUserApplicationRepository extends JpaRepository<UserApplication, Long> {

    Optional<UserApplication> findByUsername(String username);

    @Query("SELECT ua.id, ua.username, ua.email, ua.isDeleted, ua.isBlocked, ua.authType, ua.attemps,  ua.firstname, " +
            "ua.lastname, ua.country, ua.city, ua.age, ua.birthDate, ua.birthDateUtc, ua.accountCreationDate, ua.accountCreationDateUtc " +
            " FROM UserApplication ua")
    Page<IUserApplicationListProjection> findAllUsersWithPagination(Pageable pageable);

    @Query("SELECT ua.id, ua.username, ua.email, ua.isDeleted, ua.isBlocked, ua.authType, ua.attemps,  ua.firstname, " +
            "ua.lastname, ua.country, ua.city, ua.age, ua.birthDate, ua.birthDateUtc, ua.accountCreationDate, ua.accountCreationDateUtc " +
            " FROM UserApplication ua WHERE ua.accountCreationDate BETWEEN :startDate AND :endDate")
    Page<IUserApplicationListProjection> findAllUsersWithPaginationAndCreationDateRange(@Param("startDate") Instant startDate,
                                                                         @Param("endDate")Instant endDate,
                                                                         Pageable pageable);
}
