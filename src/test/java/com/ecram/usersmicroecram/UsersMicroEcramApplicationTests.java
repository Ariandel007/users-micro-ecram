package com.ecram.usersmicroecram;

import com.ecram.usersmicroecram.data.Data;
import com.ecram.usersmicroecram.dtos.response.RolAppDto;
import com.ecram.usersmicroecram.dtos.response.UserApplicationDto;
import com.ecram.usersmicroecram.exceptions.UserNotFoundedException;
import com.ecram.usersmicroecram.repositories.IRolAppRepository;
import com.ecram.usersmicroecram.repositories.IUserApplicationRepository;
import com.ecram.usersmicroecram.repositories.IUserRolRepository;
import com.ecram.usersmicroecram.services.IUserApplicationService;
import com.ecram.usersmicroecram.utils.MessageErros;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UsersMicroEcramApplicationTests {

	@MockBean
	IUserApplicationRepository userApplicationRepository;
	@MockBean
	IUserRolRepository userRolRepository;
	@MockBean
	IRolAppRepository rolAppRepository;
	@MockBean
	PasswordEncoder passwordEncoder;
	@MockBean
	ModelMapper modelMapper;

	@Autowired
	IUserApplicationService userApplicationService;


	@Test
	@DisplayName("Test Encontrar Usuario")
	void testFindUser() throws Exception {

		when(this.userApplicationRepository.findByUsername("usertest")).thenReturn(Data.findAplicacionUserTestOptional());
		when(this.modelMapper.map(Data.UserApplicationfindAplicacionUserTest(), UserApplicationDto.class)).thenReturn(Data.findAplicacionUserDto());
		when(this.userRolRepository.userRolByUserId(1L)).thenReturn(Data.listUsersRol());
		when(this.modelMapper.map(Data.getRolApp(), RolAppDto.class)).thenReturn(Data.getRolAppDto());

		UserApplicationDto userFinded = this.userApplicationService.findByUsername("usertest");

		assertEquals("usertest", userFinded.getUsername());
		assertEquals(1L, userFinded.getId());
		assertEquals("test123@gmail.com", userFinded.getEmail());
		assertEquals("Alexander", userFinded.getFirstname());
		assertEquals("Urbina", userFinded.getLastname());
		assertEquals("Peru", userFinded.getCountry());
		assertEquals("Lima", userFinded.getCity());
		assertEquals("23", userFinded.getAge());
		assertEquals(1, userFinded.getRolAppList().size());
		assertEquals("ROLE_USER", userFinded.getRolAppList().get(0).getName());
	}

	@Test
	@DisplayName("Test Encontrar Usuario que no existe")
	void testFindUserThatNotExists() throws Exception {

		when(this.userApplicationRepository.findByUsername("usr13134@ddq3")).
				thenThrow(new UserNotFoundedException(MessageErros.ERROR_USER_NOT_FOUND_MSG, MessageErros.ERROR_USER_NOT_FOUND_CODE));
		when(this.modelMapper.map(Data.UserApplicationfindAplicacionUserTest(), UserApplicationDto.class)).thenReturn(Data.findAplicacionUserDto());
		when(this.userRolRepository.userRolByUserId(1L)).thenReturn(Data.listUsersRol());
		when(this.modelMapper.map(Data.getRolApp(), RolAppDto.class)).thenReturn(Data.getRolAppDto());

		assertThrows(UserNotFoundedException.class, ()-> {
			this.userApplicationService.findByUsername("usr13134@ddq3");
		});
	}

}
