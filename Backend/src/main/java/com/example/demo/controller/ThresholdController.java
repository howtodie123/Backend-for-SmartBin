package com.example.demo.controller;

import com.example.demo.entity.threshold;
import com.example.demo.service.ThresholdService;
import com.example.demo.dto.ThresholdUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/thresholds")
@CrossOrigin(origins = "http://35.213.129.74:30566/")
public class ThresholdController {

    @Autowired
    private ThresholdService thresholdService;

    @GetMapping
    public List<threshold> getAllThresholds() {
        return thresholdService.getAllThresholds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<threshold> getThresholdById(@PathVariable Integer id) {
        Optional<threshold> threshold = thresholdService.getThresholdById(id);
        return threshold.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public threshold createThreshold(@RequestBody threshold newThreshold) {
        return thresholdService.createThreshold(newThreshold);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<threshold> updateThreshold(@PathVariable Integer id, @RequestBody ThresholdUpdateRequest updatedThreshold) {
        try {
            threshold updated = thresholdService.updateThreshold(id, updatedThreshold);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThreshold(@PathVariable Integer id) {
        try {
            thresholdService.deleteThreshold(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
