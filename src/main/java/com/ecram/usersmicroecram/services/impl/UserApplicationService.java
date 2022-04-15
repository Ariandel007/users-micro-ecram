package com.ecram.usersmicroecram.services.impl;

import com.ecram.usersmicroecram.dtos.request.UserLoginDto;
import com.ecram.usersmicroecram.dtos.request.UserRegistrationDto;
import com.ecram.usersmicroecram.dtos.response.RolAppDto;
import com.ecram.usersmicroecram.dtos.response.UserApplicationDto;
import com.ecram.usersmicroecram.dtos.response.UserCreatedDto;
import com.ecram.usersmicroecram.dtos.response.UserForListDto;
import com.ecram.usersmicroecram.exceptions.RolNotFoundException;
import com.ecram.usersmicroecram.exceptions.UserDuplicateException;
import com.ecram.usersmicroecram.exceptions.UserNotFoundedException;
import com.ecram.usersmicroecram.filters.UserListFilter;
import com.ecram.usersmicroecram.models.RolApp;
import com.ecram.usersmicroecram.models.UserApplication;
import com.ecram.usersmicroecram.models.UserRol;
import com.ecram.usersmicroecram.models.UserRolKey;
import com.ecram.usersmicroecram.repositories.IRolAppRepository;
import com.ecram.usersmicroecram.repositories.IUserApplicationRepository;
import com.ecram.usersmicroecram.repositories.IUserRolRepository;
import com.ecram.usersmicroecram.services.IUserApplicationService;
import com.ecram.usersmicroecram.utils.MessageErros;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApplicationService implements IUserApplicationService {

    private final IUserApplicationRepository userApplicationRepository;
    private final IUserRolRepository userRolRepository;
    private final IRolAppRepository rolAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserApplicationService(IUserApplicationRepository userApplicationRepository,
                                  IUserRolRepository userRolRepository,
                                  IRolAppRepository rolAppRepository,
                                  PasswordEncoder passwordEncoder,
                                  ModelMapper modelMapper) {
        this.userApplicationRepository = userApplicationRepository;
        this.userRolRepository = userRolRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.rolAppRepository = rolAppRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserApplicationDto findByUsername(String username) {
        UserApplication userFinded = this.userApplicationRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundedException(MessageErros.ERROR_USER_NOT_FOUND_MSG, MessageErros.ERROR_USER_NOT_FOUND_CODE));
        UserApplicationDto userApplicationDto = this.modelMapper.map(userFinded,UserApplicationDto.class);
        //Llamar user_rol
        List<UserRol> userRolList =  this.userRolRepository.userRolByUserId(userApplicationDto.getId());
        //Mapear solo los roles
        List<RolAppDto> rolAppDtoList = userRolList.stream()
                .map(userRol ->this.modelMapper.map(userRol.getRolApp(),RolAppDto.class))
                .collect(Collectors.toList());
        //Setear Roles
        userApplicationDto.setRolAppList(rolAppDtoList);

        return userApplicationDto;
    }

    private boolean userNameExists(String username) {
        Optional<UserApplication> userFinded = this.userApplicationRepository.findByUsername(username);
        return userFinded.isPresent();
    }

    @Override
    @Transactional(readOnly = false)
    public UserCreatedDto registerUser(UserRegistrationDto userRegistrationDto) {
        if(this.userNameExists(userRegistrationDto.getUsername())){
            throw new UserDuplicateException(MessageErros.ERROR_USER_ALREADY_EXISTS_MSG, MessageErros.ERROR_USER_ALREADY_EXISTS_CODE);
        }
        //Usamos bcrypt para decodificarlo
        userRegistrationDto.setPassword(this.passwordEncoder.encode(userRegistrationDto.getPassword()));

        //convertimos en DTO a una entidad
        UserApplication userApplicationToCreate = this.modelMapper.map(userRegistrationDto, UserApplication.class);

        //Parseamos y calculamos los tiempos
        Instant instantBirthDate= Instant.parse(userRegistrationDto.getBirthDateUtc());
        Instant today = Instant.now();
        long age = ChronoUnit.YEARS.between(instantBirthDate.atZone(ZoneId.systemDefault()),today.atZone(ZoneId.systemDefault()));

        userApplicationToCreate.setBirthDate(instantBirthDate);
        userApplicationToCreate.setAccountCreationDate(today);
        userApplicationToCreate.setAccountCreationDateUtc(today.toString());
        userApplicationToCreate.setAge(Long.toString(age));

        //Guardamos
        UserApplication userApplicationCreated = this.userApplicationRepository.save(userApplicationToCreate);

        //LLAMAMOS AL ROL_USER
        RolApp rolApp = this.rolAppRepository.findById(1L).orElseThrow(()->new RolNotFoundException("El rol no fue encontrado", "ERROR_ROL_NOT_FOUNDED"));

        //Asociamos el usuario al ROLE_USER
        UserRol userRol = new UserRol();
        userRol.setId(new UserRolKey(userApplicationCreated.getId(), rolApp.getId()));
        userRol.setRolApp(rolApp);
        userRol.setUserApplication(userApplicationCreated);
        this.userRolRepository.save(userRol);

        //Comvertimos la entidad guardara a el DTO del response
        return this.modelMapper.map(userApplicationCreated, UserCreatedDto.class);
    }

    @Override
    public UserApplication loginUser(UserLoginDto userLoginDto) {
//        UserApplication userApplicationFinded = this.findByUsername(userLoginDto.getUsername());
//        if(this.passwordEncoder.matches(userLoginDto.getPassword(),userApplicationFinded.getPassword()))
//        {
//            //TODO: Login procedera
//        } else {
//            //TODO: NO SE LOGEARA
//        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserForListDto> findUserFiltered(UserListFilter userListFilter) {
        int page = userListFilter.getPage();//inicia en 0
        int size = userListFilter.getSize();
        Pageable pageable;
        if(userListFilter.isAscendingUserId()) {
            pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }

        if(userListFilter.getEndDate() == null && userListFilter.getStartDate() == null) {
            return this.userApplicationRepository.findAllUsersWithPagination(pageable);
        }

        return this.userApplicationRepository.findAllUsersWithPaginationAndCreationDateRange(
                userListFilter.getStartDate(),
                userListFilter.getEndDate(),
                pageable);
    }

}
