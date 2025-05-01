package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SignInRequest {
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;
}
