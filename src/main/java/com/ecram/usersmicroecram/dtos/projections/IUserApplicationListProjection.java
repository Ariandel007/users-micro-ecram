package com.ecram.usersmicroecram.dtos.projections;

import java.time.Instant;

public interface IUserApplicationListProjection {

    Long getId();

    String getUsername();

    String getEmail();

    Boolean getIsDeleted();

    Boolean getIsBlocked();

    String getAuthType();

    Short getAttemps();

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
