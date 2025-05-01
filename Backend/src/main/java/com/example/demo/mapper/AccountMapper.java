package com.example.demo.mapper;

import com.example.demo.dto.SignInRequest;
import com.example.demo.entity.User;


public class AccountMapper {

    public static User toAccount(SignInRequest signInRequest) {
        return new User();
    }

}
