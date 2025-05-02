package com.example.demo.service.impl;

import com.example.demo.entity.threshold;
import com.example.demo.repository.ThresholdRepository;
import com.example.demo.service.ThresholdService;
import com.example.demo.dto.ThresholdUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ThresholdServiceImpl implements ThresholdService {

    @Autowired
    private ThresholdRepository thresholdRepository;

    @Override
    public Optional<threshold> getThresholdByIdbin(Integer idbin) {
        return thresholdRepository.findByIdbin(idbin);
    }

    @Override
    public List<threshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }

    @Override
    public Optional<threshold> getThresholdById(Integer id) {
        return thresholdRepository.findById(id);
    }

    @Override
    public threshold createThreshold(threshold newThreshold) {
        return thresholdRepository.save(newThreshold);
    }

    @Override
    public threshold updateThreshold(Integer id, ThresholdUpdateRequest updatedThreshold) {
        // Tìm kiếm threshold theo ID
        Optional<threshold> existingThreshold = thresholdRepository.findById(id);

        // Nếu không tìm thấy threshold, ném ra exception
        if (existingThreshold.isPresent()) {
            threshold thresholdToUpdate = existingThreshold.get();

            // Chỉ cập nhật các trường cần thiết
            thresholdToUpdate.setStorage1(updatedThreshold.getStorage1());
            thresholdToUpdate.setStorage2(updatedThreshold.getStorage2());
            thresholdToUpdate.setStorage3(updatedThreshold.getStorage3());
            thresholdToUpdate.setStorage4(updatedThreshold.getStorage4());
            thresholdToUpdate.setTemperature(updatedThreshold.getTemperature());

            // Lưu lại các thay đổi vào database
            return thresholdRepository.save(thresholdToUpdate);
        } else {
            // Ném ra lỗi nếu không tìm thấy threshold với id đó
            throw new RuntimeException("Threshold with ID " + id + " not found.");
        }
    }


    @Override
    public void deleteThreshold(Integer id) {
        if (thresholdRepository.existsById(id)) {
            thresholdRepository.deleteById(id);
        } else {
            throw new RuntimeException("Threshold with ID " + id + " not found.");
        }
    }
}
