package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class AccountCreate {
    private String role;
    private String name;
    private String password;
    private String username;
}
