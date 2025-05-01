package com.example.demo.service;

import com.example.demo.entity.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    void save(User user);
}
