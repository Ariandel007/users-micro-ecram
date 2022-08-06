package com.ecram.usersmicroecram.controllers;

import com.ecram.usersmicroecram.dtos.request.UserRegistrationDto;
import com.ecram.usersmicroecram.dtos.response.UserApplicationDto;
import com.ecram.usersmicroecram.dtos.response.UserCreatedDto;
import com.ecram.usersmicroecram.dtos.response.UserForListDto;
import com.ecram.usersmicroecram.filters.UserListFilter;
import com.ecram.usersmicroecram.services.IUserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final IUserApplicationService userApplicationService;

    @Autowired
    public UserController(IUserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping("/findUser/{username}")
    ResponseEntity<UserApplicationDto> getUserByUsername(@PathVariable String username) {
        UserApplicationDto userApplication = this.userApplicationService.findByUsername(username);
        return ResponseEntity.ok().body(userApplication);
    }

    @PostMapping("/createUser")
    ResponseEntity<UserCreatedDto> createUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        UserCreatedDto userCreatedDto = this.userApplicationService.registerUser(userRegistrationDto);
        return ResponseEntity.ok().body(userCreatedDto);
    }

    @PostMapping("/findUsers")
    ResponseEntity<Page<UserForListDto>> findUserFiltered(@RequestBody UserListFilter userListFilter) {
        Page<UserForListDto> usersFiltered = this.userApplicationService.findUserFiltered(userListFilter);
        return ResponseEntity.ok().body(usersFiltered);
    }

    @GetMapping("/findUserId/{username}")
    ResponseEntity<Long> getUserIdByUsername(@PathVariable String username) {
        Long userId = this.userApplicationService.getUserIdByUsername(username);
        return ResponseEntity.ok().body(userId);
    }

}
