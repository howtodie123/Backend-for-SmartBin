package com.example.demo.service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<ApiResponse<?>> signUp(SignUpRequest signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException;
    ResponseEntity<ApiResponse<?>> signIn(SignInRequest signInRequestDto);
}
