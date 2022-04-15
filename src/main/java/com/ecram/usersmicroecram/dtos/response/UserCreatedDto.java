package com.ecram.usersmicroecram.dtos.response;

import lombok.Data;

import java.time.Instant;

@Data
public class UserCreatedDto {
    private String username;
//    private String password;
    private String email;
    private String authType;
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
