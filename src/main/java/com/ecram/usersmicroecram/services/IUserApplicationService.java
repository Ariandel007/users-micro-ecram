package com.ecram.usersmicroecram.services;

import com.ecram.usersmicroecram.dtos.projections.IUserApplicationListProjection;
import com.ecram.usersmicroecram.dtos.request.UserLoginDto;
import com.ecram.usersmicroecram.dtos.request.UserRegistrationDto;
import com.ecram.usersmicroecram.dtos.response.UserApplicationDto;
import com.ecram.usersmicroecram.dtos.response.UserCreatedDto;
import com.ecram.usersmicroecram.dtos.response.UserForListDto;
import com.ecram.usersmicroecram.filters.UserListFilter;
import com.ecram.usersmicroecram.models.UserApplication;
import org.springframework.data.domain.Page;

public interface IUserApplicationService {
    UserApplicationDto findByUsername(String username) throws Exception;

    UserCreatedDto registerUser(UserRegistrationDto userRegistrationDto) throws Exception;

    //Esta funcion la hara el oauth en sí, aca solo esy
    UserApplication loginUser(UserLoginDto userLoginDto) throws Exception;

    Page<UserForListDto> findUserFiltered(UserListFilter userListFilter) throws Exception;

}
