package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entity.databin;

@Repository
public interface DataBinRepository extends JpaRepository<databin, Integer>{
    @Query(value = "SELECT * FROM databin ORDER BY id DESC LIMIT 1", nativeQuery = true)
    databin findLatestData();
}
