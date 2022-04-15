package com.ecram.usersmicroecram.data;

import com.ecram.usersmicroecram.dtos.response.RolAppDto;
import com.ecram.usersmicroecram.dtos.response.UserApplicationDto;
import com.ecram.usersmicroecram.models.RolApp;
import com.ecram.usersmicroecram.models.UserApplication;
import com.ecram.usersmicroecram.models.UserRol;
import com.ecram.usersmicroecram.models.UserRolKey;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Data {
    public static Optional<UserApplication> findAplicacionUserTestOptional() {
        UserApplication userApplication = new UserApplication();
        userApplication.setId(1L);
        userApplication.setUsername("usertest");
        userApplication.setPassword("$2a$10$s8WTqvgyM3tT4AvGkkDbpOwHD981ylV1/EMJQJtvWmi0O/Ncb.xZ.");
        userApplication.setEmail("test123@gmail.com");
        userApplication.setFirstname("Alexander");
        userApplication.setLastname("Urbina");
        userApplication.setCountry("Peru");
        userApplication.setCity("Lima");
        userApplication.setAge("23");
        userApplication.setBirthDate(Instant.parse("1999-02-20T00:34:29.235Z"));
        userApplication.setBirthDateUtc("1999-02-20T00:34:29.235Z");
        userApplication.setAccountCreationDate(Instant.parse("2022-04-15T03:44:07.573716700Z"));
        userApplication.setAccountCreationDateUtc("2022-04-15T03:44:07.573716700Z");

        return Optional.of(userApplication);
    }

    public static UserApplication UserApplicationfindAplicacionUserTest() {
        UserApplication userApplication = new UserApplication();
        userApplication.setId(1L);
        userApplication.setUsername("usertest");
        userApplication.setPassword("$2a$10$s8WTqvgyM3tT4AvGkkDbpOwHD981ylV1/EMJQJtvWmi0O/Ncb.xZ.");
        userApplication.setEmail("test123@gmail.com");
        userApplication.setFirstname("Alexander");
        userApplication.setLastname("Urbina");
        userApplication.setCountry("Peru");
        userApplication.setCity("Lima");
        userApplication.setAge("23");
        userApplication.setBirthDate(Instant.parse("1999-02-20T00:34:29.235Z"));
        userApplication.setBirthDateUtc("1999-02-20T00:34:29.235Z");
        userApplication.setAccountCreationDate(Instant.parse("2022-04-15T03:44:07.573716700Z"));
        userApplication.setAccountCreationDateUtc("2022-04-15T03:44:07.573716700Z");

        return userApplication;
    }

    public static UserApplicationDto findAplicacionUserDto() {
        UserApplicationDto userApplicationDto = new UserApplicationDto();
        userApplicationDto.setId(1L);
        userApplicationDto.setUsername("usertest");
        userApplicationDto.setPassword("$2a$10$s8WTqvgyM3tT4AvGkkDbpOwHD981ylV1/EMJQJtvWmi0O/Ncb.xZ.");
        userApplicationDto.setEmail("test123@gmail.com");
        userApplicationDto.setFirstname("Alexander");
        userApplicationDto.setLastname("Urbina");
        userApplicationDto.setCountry("Peru");
        userApplicationDto.setCity("Lima");
        userApplicationDto.setAge("23");
        userApplicationDto.setBirthDate(Instant.parse("1999-02-20T00:34:29.235Z"));
        userApplicationDto.setBirthDateUtc("1999-02-20T00:34:29.235Z");
        userApplicationDto.setAccountCreationDate(Instant.parse("2022-04-15T03:44:07.573716700Z"));
        userApplicationDto.setAccountCreationDateUtc("2022-04-15T03:44:07.573716700Z");


        return userApplicationDto;
    }

    public static List<UserRol> listUsersRol() {
        List<UserRol> userRolList = new ArrayList<UserRol>();
        UserRol userRol = new UserRol();
        userRol.setId(new UserRolKey(1L,1L));
        RolApp rolApp = new RolApp();
        rolApp.setId(1L);
        rolApp.setName("ROLE_USER");
        userRol.setRolApp(rolApp);

        userRolList.add(userRol);
        return userRolList;
    }

    public static RolApp getRolApp() {
        RolApp rolApp = new RolApp();
        rolApp.setId(1L);
        rolApp.setName("ROLE_USER");
        return rolApp;
    }

    public static RolAppDto getRolAppDto() {
        RolAppDto rolAppDto = new RolAppDto();
        rolAppDto.setId(1L);
        rolAppDto.setName("ROLE_USER");
        return rolAppDto;
    }
}
