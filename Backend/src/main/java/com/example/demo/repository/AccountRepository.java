package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.account;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<account,Integer >{
    account findByUsernameAndPassword(String username,String password);
    boolean existsByUsername(String username);
}



