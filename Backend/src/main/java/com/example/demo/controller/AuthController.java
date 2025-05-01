package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:63342/")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody @Valid SignUpRequest signUpRequest)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<?>> signInUser(@RequestBody @Valid SignInRequest signInRequest){
        return authService.signIn(signInRequest);
    }

}
