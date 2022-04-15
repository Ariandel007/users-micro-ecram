package com.ecram.usersmicroecram.controllers;

import com.ecram.usersmicroecram.data.Data;
import com.ecram.usersmicroecram.services.IUserApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    IUserApplicationService userApplicationService;


    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Test Encontrar Usuario - Controller")
    void testFindUserController() throws Exception {
        //GIVEN
        when(this.userApplicationService.findByUsername("usertest")).thenReturn(Data.findAplicacionUserDtoComplete());

        //WHEN
        mvc.perform(get("/v1/users/findUser/usertest").contentType(MediaType.APPLICATION_JSON))
        //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("usertest"))
                .andExpect(jsonPath("$.firstname").value("Alexander"));

        verify(userApplicationService).findByUsername("usertest");


    }

}
