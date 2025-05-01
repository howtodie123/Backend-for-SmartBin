package com.example.demo.controller;
import com.example.demo.dto.EmailRequest;
import com.example.demo.service.EmailService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.dto.AccountCreate;
import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.entity.account;
import com.example.demo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:63342") //63342
public class AccountController {

    private final AccountService accountService;
    @Autowired
    private EmailService1 emailService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<account>> getAllAccounts() {
        List<account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AccountResponse>  authenticate(AccountRequest accountRequest) {
//        AccountResponse response = accountService.authenticateUser(accountRequest);
//        if (response != null) {
//            return ResponseEntity.ok(response);
//        } else {
//            // Trả về lỗi nếu tài khoản hoặc mật khẩu sai
//            throw new RuntimeException(" username or password");
//        }
//    }
@PostMapping("/authenticate")
public AccountResponse authenticate(@RequestBody AccountRequest accountRequest) {
    AccountResponse response = accountService.authenticateUser(accountRequest);
    if (response != null) {
        return response;
    } else {
        // Trả về lỗi nếu tài khoản hoặc mật khẩu sai
        throw new RuntimeException(" username or password");
    }
}

// Đăng kí tài khoản


    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody AccountCreate accountCreate) {
        try {
            AccountResponse response = accountService.registerAccount(accountCreate);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendSimpleEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
        return "Email sent successfully";
    }
    
}
