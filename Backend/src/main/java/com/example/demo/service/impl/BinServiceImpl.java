package com.example.demo.service.impl;

import com.example.demo.dto.BinRequest;
import com.example.demo.entity.bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;

    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    // Lấy danh sách tất cả các bin
    public List<bin> getAllBins() {
        return binRepository.findAll();
    }

    // Lấy chi tiết bin theo ID
    public Optional<bin> getBinById(Integer id) {
        return binRepository.findById(id);
    }

    // Tạo mới bin
    public bin createBin(bin newBin) {
        return binRepository.save(newBin);
    }

    // Cập nhật thông tin bin theo ID (sử dụng DTO)
    public Optional<bin> updateBin(Integer id, BinRequest binRequest) {
        Optional<bin> optionalBin = binRepository.findById(id);
        if (optionalBin.isPresent()) {
            bin existingBin = optionalBin.get();
            existingBin.setName(binRequest.getName());
            existingBin.setLocationX(binRequest.getLocationX());
            existingBin.setLocationY(binRequest.getLocationY());
            return Optional.of(binRepository.save(existingBin));
        }
        return Optional.empty();
    }

    // Xóa bin theo ID
    public boolean deleteBin(Integer id) {
        if (binRepository.existsById(id)) {
            binRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
