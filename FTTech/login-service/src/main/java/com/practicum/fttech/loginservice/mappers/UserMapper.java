package com.practicum.fttech.loginservice.mappers;

import com.practicum.fttech.loginservice.dto.UserCreationDto;
import com.practicum.fttech.loginservice.dto.UserDto;
import com.practicum.fttech.loginservice.entites.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.Converter;


@Mapper(componentModel = "spring")
public interface UserMapper  {


    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.login", target = "login")
    @Mapping(source = "token", target = "token")
    @Mapping(source = "user.role", target = "role")
    UserDto toUserDto(User user, String token);

    @Mapping(source = "encodedPassword", target = "passwordHash")
    User toBookstoreUser(UserCreationDto userDto, String encodedPassword);
}