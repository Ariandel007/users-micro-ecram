package com.ecram.usersmicroecram.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForListDto {
    private Long id;

    private String username;

    private String email;

    private Boolean isDeleted;

    private Boolean isBlocked;

    private String authType;

    private Short attemps;

    private String firstname;

    private String lastname;

    private String country;

    private String city;

    private String age;

    private Instant birthDate;

    private String birthDateUtc;

    private Instant accountCreationDate;

    private String accountCreationDateUtc;
}
