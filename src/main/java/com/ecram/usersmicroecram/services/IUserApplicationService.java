package com.ecram.usersmicroecram.services;

import com.ecram.usersmicroecram.dtos.request.UserRegistrationDto;
import com.ecram.usersmicroecram.models.UserApplication;

public interface IUserApplicationService {
    UserApplication findByUsername(String username);

    UserApplication registerUser(UserRegistrationDto userRegistrationDto);

}
