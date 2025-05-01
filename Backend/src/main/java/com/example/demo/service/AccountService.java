package com.example.demo.service;

import com.example.demo.dto.AccountCreate;
import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.AccountResponse;
import com.example.demo.entity.account;

import java.util.*;


public interface AccountService {
    AccountResponse authenticateUser(AccountRequest accountRequest);
    List<account> getAllAccounts();
    AccountResponse registerAccount(AccountCreate accountCreate);
    //AccountCreate createAccount(AccountCreate accountRequest);
   // boolean checkIfUsernameExists(String username);
}
