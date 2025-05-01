package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.threshold;

import java.util.*;

public interface ThresholdRepository extends JpaRepository<threshold, Integer>{
    Optional<threshold> findByIdbin(Integer idbin);
}
