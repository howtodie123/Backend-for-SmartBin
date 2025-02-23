package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.AccountCreate;
import com.example.demo.entity.account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse authenticateUser(AccountRequest accountRequest) {
        // Tìm kiếm tài khoản trong cơ sở dữ liệu theo username
        account account1 = accountRepository.findByUsernameAndPassword(accountRequest.getUsername(), accountRequest.getPassword());
        return new AccountResponse(account1.getId(), account1.getRole(), account1.getName());
    }

    public List<account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountResponse registerAccount(AccountCreate accountCreate) {
        // Kiểm tra tên người dùng có tồn tại không
        if (accountRepository.existsByUsername(accountCreate.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Lưu thông tin tài khoản mới
        account account1 = new account();
        account1.setName(accountCreate.getName());
        account1.setRole(accountCreate.getRole());
        account1.setUsername(accountCreate.getUsername());
        account1.setPassword(accountCreate.getPassword());

        account savedAccount = accountRepository.save(account1);

        // Trả về DTO AccountResponse
        return new AccountResponse(savedAccount.getId(), savedAccount.getRole(), savedAccount.getName());
    }

//    @Override
//    public boolean checkIfUsernameExists(String username) {
//        return accountRepository.existsByUsername(username);
//    }
}
