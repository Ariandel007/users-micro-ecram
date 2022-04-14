package com.ecram.usersmicroecram.services.impl;

import com.ecram.usersmicroecram.dtos.projections.IUserApplicationListProjection;
import com.ecram.usersmicroecram.dtos.request.UserLoginDto;
import com.ecram.usersmicroecram.dtos.request.UserRegistrationDto;
import com.ecram.usersmicroecram.dtos.response.UserCreatedDto;
import com.ecram.usersmicroecram.dtos.response.UserForListDto;
import com.ecram.usersmicroecram.exceptions.UserDuplicateException;
import com.ecram.usersmicroecram.exceptions.UserNotFoundedException;
import com.ecram.usersmicroecram.filters.UserListFilter;
import com.ecram.usersmicroecram.models.UserApplication;
import com.ecram.usersmicroecram.repositories.IUserApplicationRepository;
import com.ecram.usersmicroecram.services.IUserApplicationService;
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
import java.util.Optional;

@Service
public class UserApplicationService implements IUserApplicationService {

    private final IUserApplicationRepository userApplicationRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserApplicationService(IUserApplicationRepository userApplicationRepository,
                                  PasswordEncoder passwordEncoder,
                                  ModelMapper modelMapper) {
        this.userApplicationRepository = userApplicationRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserApplication findByUsername(String username) throws Exception {
        Optional<UserApplication> userFinded = this.userApplicationRepository.findByUsername(username);
        return userFinded.orElseThrow(() -> new UserNotFoundedException("El usuario no existe", "ERROR_USER_NOT_FOUND"));
    }

    private boolean userNameExists(String username) {
        Optional<UserApplication> userFinded = this.userApplicationRepository.findByUsername(username);
        return userFinded.isPresent();
    }

    @Override
    @Transactional
    public UserCreatedDto registerUser(UserRegistrationDto userRegistrationDto) throws Exception {
        if(this.userNameExists(userRegistrationDto.getUsername())){
            throw new UserDuplicateException("El usuario esta duplicado", "ERROR_USER_ALREADY_EXISTS");
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

        //Comvertimos la entidad guardara a el DTO del response
        return this.modelMapper.map(userApplicationCreated, UserCreatedDto.class);
    }

    @Override
    public UserApplication loginUser(UserLoginDto userLoginDto) throws Exception {
        UserApplication userApplicationFinded = this.findByUsername(userLoginDto.getUsername());
        if(this.passwordEncoder.matches(userLoginDto.getPassword(),userApplicationFinded.getPassword()))
        {
            //TODO: Login procedera
        } else {
            //TODO: NO SE LOGEARA
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IUserApplicationListProjection> findUserFiltered(UserListFilter userListFilter) throws Exception {
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
