package com.example.demo.service;

import com.example.demo.dto.ThresholdUpdateRequest;
import com.example.demo.entity.threshold;

import java.util.*;

public interface ThresholdService {

        List<threshold> getAllThresholds();                  // Lấy danh sách tất cả thresholds

        Optional<threshold> getThresholdById(Integer id);    // Lấy threshold theo ID

        threshold createThreshold(threshold newThreshold);  // Tạo mới threshold

        threshold updateThreshold(Integer id, ThresholdUpdateRequest updatedThreshold); // Cập nhật threshold

        void deleteThreshold(Integer id);                   // Xóa threshold theo ID

        Optional<threshold> getThresholdByIdbin(Integer idbin);
}
