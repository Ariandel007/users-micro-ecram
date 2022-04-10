package com.ecram.usersmicroecram.services.impl;

import com.ecram.usersmicroecram.dtos.request.UserRegistrationDto;
import com.ecram.usersmicroecram.models.UserApplication;
import com.ecram.usersmicroecram.services.IUserApplicationService;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService implements IUserApplicationService {
    @Override
    public UserApplication findByUsername(String username) {
        return null;
    }

    @Override
    public UserApplication registerUser(UserRegistrationDto userRegistrationDto) {
        return null;
    }

}
