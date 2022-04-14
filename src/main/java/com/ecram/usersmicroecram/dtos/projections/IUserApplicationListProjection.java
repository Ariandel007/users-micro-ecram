package com.ecram.usersmicroecram.dtos.projections;

import java.time.Instant;

public interface IUserApplicationListProjection {

    Long getId();

    String getUsername();

    String getEmail();

    boolean isDeleted();

    boolean isBlocked();

    String getAuthType();

    short getAttemps();

    String getFirstname();

    String getLastname();

    String getCountry();

    String getCity();

    String getAge();

    Instant getBirthDate();

    String getBirthDateUtc();

    Instant getAccountCreationDate();

    String getAccountCreationDateUtc();
}
