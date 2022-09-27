package com.practicum.fttech.loginservice.controllers;


import com.practicum.fttech.loginservice.dto.CredentialsDto;
import com.practicum.fttech.loginservice.dto.UserCreationDto;
import com.practicum.fttech.loginservice.dto.UserDto;
import com.practicum.fttech.loginservice.entites.LoginResponseModel;
import com.practicum.fttech.loginservice.services.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService userService;


    @PostMapping("/signIn")
    public ResponseEntity<UserDto> signIn(@RequestBody CredentialsDto credentialsDto) {
        log.info("Trying to login {}", credentialsDto.getLogin());
        return ResponseEntity.ok(userService.signIn(credentialsDto));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<UserDto> signIn(@RequestParam String token) {
        log.info("Trying to validate token {}", token);
        return ResponseEntity.ok(userService.validateToken(token));
    }


}