package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> Test() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(String.valueOf(ResponseStatus.SUCCESS))
                        .message("Securing Spring Boot using Spring Security and JWT")
                        .build());
    }

    //Only users with 'ROLE_USER' role can access this end point
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ApiResponse<?>> UserDashboard() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(String.valueOf(ResponseStatus.SUCCESS))
                        .message("User dashboard!")
                        .build());
    }

    //    Only users with 'ROLE_ADMIN' role can access this end point
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<?>> AdminDashboard() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(String.valueOf(ResponseStatus.SUCCESS))
                        .message("Admin dashboard!")
                        .build());
    }

    //    Only users with 'ROLE_SUPER_ADMIN' role can access this end point
    @GetMapping("/super-admin")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<ApiResponse<?>> SuperAdminDashboard() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(String.valueOf(ResponseStatus.SUCCESS))
                        .message("Super Admin dashboard!")
                        .build());
    }

    //    Users with 'ROLE_SUPER_ADMIN' or 'ROLE_ADMIN' roles can access this end point
    @GetMapping("/admin-or-super-admin")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<?>> AdminOrSuperAdminContent() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.builder()
                        .status(String.valueOf(ResponseStatus.SUCCESS))
                        .message("Admin or Super Admin Content!")
                        .build());
    }
}