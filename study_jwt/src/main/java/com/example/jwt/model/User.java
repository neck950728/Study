package com.example.jwt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String roles;

    @Column(length = 1000)
    private String refreshToken;

    private Long refreshTokenExpiresAt;

    public List<String> getRoleList() {
        if(roles.length() > 0) return Arrays.asList(roles.split(","));
        return new ArrayList<>();
    }
}