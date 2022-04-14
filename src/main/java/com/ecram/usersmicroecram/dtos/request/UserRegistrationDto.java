package com.ecram.usersmicroecram.dtos.request;

import lombok.Data;

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
    private String birthDateUtc;

}
