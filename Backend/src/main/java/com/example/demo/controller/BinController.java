package com.example.demo.controller;

import com.example.demo.dto.BinRequest;
import com.example.demo.entity.bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bins")
@CrossOrigin(origins = "http://localhost:63342")
public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<bin>> getAllBins() {
        List<bin> bins = binService.getAllBins();
        return ResponseEntity.ok(bins);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<bin> getBinById(@PathVariable Integer id) {
        Optional<bin> optionalBin = binService.getBinById(id);
        return optionalBin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST (Create new bin)
    @PostMapping
    public ResponseEntity<bin> createBin(@RequestBody BinRequest binRequest) {
        // Chuyển DTO thành entity
        bin newBin = new bin();
        newBin.setName(binRequest.getName());
        newBin.setLocation(binRequest.getLocation());
        bin savedBin = binService.createBin(newBin);
        return ResponseEntity.ok(savedBin);
    }

    // PUT (Update bin by ID)
    @PutMapping("/{id}")
    public ResponseEntity<bin> updateBin(@PathVariable Integer id, @RequestBody BinRequest binRequest) {
        Optional<bin> optionalUpdatedBin = binService.updateBin(id, binRequest);
        return optionalUpdatedBin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE (Delete bin by ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBin(@PathVariable Integer id) {
        boolean deleted = binService.deleteBin(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
