package com.example.demo.service;

import com.example.demo.entity.bin;
import com.example.demo.dto.BinRequest;
import java.util.*;

public interface BinService {
    List<bin> getAllBins();
    Optional<bin> getBinById(Integer id);
    bin createBin(bin newBin);
    Optional<bin> updateBin(Integer id, BinRequest updatedBin);
    boolean deleteBin(Integer id);
}
