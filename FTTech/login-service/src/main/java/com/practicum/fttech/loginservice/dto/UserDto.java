package com.practicum.fttech.loginservice.dto;


import com.practicum.fttech.loginservice.entites.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.rmi.server.UID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {

    private long id;
    private String login;
    private String token;
    private  Role role;
}


