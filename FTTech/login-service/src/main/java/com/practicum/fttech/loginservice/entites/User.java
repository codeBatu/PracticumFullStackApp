package com.practicum.fttech.loginservice.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.rmi.server.UID;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(
        name = "users_information"

)
public class User {

    @Id
    @SequenceGenerator(name = "seq_user",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    private Long id;
    @Column(name = "name")
    private String name ;
    @Column(name = "email")
    private String email ;
    @Column(name = "password_hash")
    private String passwordHash ;
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "account_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long AccountId;



    @Column(name = "login")
    private String login;


}
