package com.ecram.usersmicroecram.dtos.request;

import lombok.Data;

@Data
public class UserChangePasswordDto {
    private String username;
    private String password;
    private String email;
}
