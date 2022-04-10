package com.ecram.usersmicroecram.dtos.request;

import lombok.Data;

@Data
public class UserLoginDto {
    private String username;
    private String password;
}
