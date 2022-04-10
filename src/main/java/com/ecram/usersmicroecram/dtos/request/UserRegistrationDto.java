package com.ecram.usersmicroecram.dtos.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegistrationDto {
    private String username;
    private String password;
    private String email;
    private String authType;
    private String firstname;
    private String lastname;
    private String country;
    private String city;
    private String age;
    private Date birthDate;
//    private String birthDateUtc;
    private Date accountCreationDate;
//    private String accountCreationDateUtc;

}
