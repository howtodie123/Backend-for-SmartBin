package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.bin;

import java.util.*;

@Repository
public interface BinRepository extends JpaRepository<bin, Integer> {

}
