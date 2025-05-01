package com.example.demo.service.impl;

import com.example.demo.dto.BinRequest;
import com.example.demo.entity.bin;
<<<<<<< HEAD
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
=======
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> ca8492138dd65490aacc75e7e394a40feee64289
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
            existingBin.setLocation(binRequest.getLocation());
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
