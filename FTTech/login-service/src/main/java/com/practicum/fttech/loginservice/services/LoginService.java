package com.practicum.fttech.loginservice.services;


import com.practicum.fttech.loginservice.dto.CredentialsDto;
import com.practicum.fttech.loginservice.dto.UserCreationDto;
import com.practicum.fttech.loginservice.dto.UserDto;
import com.practicum.fttech.loginservice.entites.LoginResponseModel;
import com.practicum.fttech.loginservice.entites.Role;
import com.practicum.fttech.loginservice.entites.User;
import com.practicum.fttech.loginservice.exception.LoginException;
import com.practicum.fttech.loginservice.mappers.UserMapper;
import com.practicum.fttech.loginservice.repos.ILoginRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final ILoginRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    private String secretKey = "secretKey";


    @PostConstruct
    private void init() {
        System.out.println("User Service");
        var list=   userRepository.findAll().stream().toList();
        if (list.isEmpty()){
//            User user = new User();
//            user.setUsername("admin");
//            user.setPassword(passwordEncoder.encode("admin"));
//            userRepository.save(user);
            var user = new User();
            user.setEmail("admin");
            user.setLogin("admin");

            user.setRole(Role.ROLE_ADMIN);
            user.setPasswordHash(passwordEncoder.encode(CharBuffer.wrap("admin")));

            var user2 = new User();
            user2.setRole(Role.ROLE_USER);
            user2.setLogin("string");
            user2.setEmail("string");
            user2.setPasswordHash(passwordEncoder.encode(CharBuffer.wrap("string")));
            userRepository.save(user2);
            userRepository.save(user);
            System.out.println(user);
        }

            secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

    }








    public UserDto signIn(CredentialsDto credentialsDto) {
        var user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new LoginException("User not found", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPasswordHash())) {
            return userMapper.toUserDto(user, createToken(user));
        }

        throw new LoginException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto validateToken(String token) {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<User> userOptional = userRepository.findByLogin(login);

        if (((Optional<?>) userOptional).isEmpty()) {
            throw new LoginException("User not found", HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();
        return userMapper.toUserDto(user, createToken(user));
    }

    private String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }



}