package com.example.demo.mapper;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountCreate;
import com.example.demo.entity.account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;



public class AccountMapper {

    public static account toAccount(AccountRequest accountRequest) {
        return new account();
    }

}
