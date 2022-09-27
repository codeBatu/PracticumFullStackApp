package com.practicum.fttech.loginservice.mappers;

import com.practicum.fttech.loginservice.dto.UserCreationDto;
import com.practicum.fttech.loginservice.dto.UserDto;
import com.practicum.fttech.loginservice.dto.UserDto.UserDtoBuilder;
import com.practicum.fttech.loginservice.entites.User;
import com.practicum.fttech.loginservice.entites.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-27T19:26:57+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user, String token) {
        if ( user == null && token == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        if ( user != null ) {
            if ( user.getId() != null ) {
                userDto.id( user.getId() );
            }
            userDto.login( user.getLogin() );
            userDto.role( user.getRole() );
        }
        if ( token != null ) {
            userDto.token( token );
        }

        return userDto.build();
    }

    @Override
    public User toBookstoreUser(UserCreationDto userDto, String encodedPassword) {
        if ( userDto == null && encodedPassword == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        if ( userDto != null ) {
            user.login( userDto.getLogin() );
        }
        if ( encodedPassword != null ) {
            user.passwordHash( encodedPassword );
        }

        return user.build();
    }
}
