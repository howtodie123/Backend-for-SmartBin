package com.example.demo.controller;

import com.example.demo.entity.warning;
import com.example.demo.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/warnings")
@CrossOrigin(origins = "http://35.213.129.74:30566/")
public class WarningController {

    @Autowired
    private WarningService warningService;

    // Hiển thị toàn bộ cảnh báo
    @GetMapping
    public List<warning> getAllWarnings() {
        return warningService.getAllWarnings();
    }

    // Hiển thị các cảnh báo của idbin được yêu cầu đến
    @GetMapping("/bin/{idbin}")
    public List<warning> getWarningsByIdBin(@PathVariable Integer idbin) {
        return warningService.getWarningsByIdBin(idbin);
    }

    // Tạo một warning mới
    @PostMapping
    public warning createWarning(@RequestBody warning warning) {
        return warningService.createWarning(warning);
    }

    // Xóa cảnh báo theo id
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarningById(@PathVariable Integer id) {
        String result = warningService.deleteWarningById(id);
        if (result.contains("not found")) {
            return ResponseEntity.status(404).body(result);
        }
        return ResponseEntity.ok(result);
    }

    // Xóa toàn bộ cảnh báo
    @DeleteMapping
    public ResponseEntity<String> deleteAllWarnings() {
        return ResponseEntity.ok(warningService.deleteAllWarnings());
    }
}
