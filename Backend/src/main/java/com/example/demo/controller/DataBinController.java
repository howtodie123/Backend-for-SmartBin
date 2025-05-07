package com.example.demo.controller;

import com.example.demo.dto.DataBinRequest;
import com.example.demo.entity.databin;
import com.example.demo.entity.warning;
import com.example.demo.service.DataBinService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.service.EmailService;


import java.util.List;

@RestController
@RequestMapping("/api/databins")
@CrossOrigin(origins = "http://35.213.129.74:30566/")
public class DataBinController {


    private final DataBinService dataBinService;
    //private final EmailService emailService;

    public DataBinController(DataBinService dataBinService) {
        this.dataBinService = dataBinService;
    }

    // API lấy toàn bộ giá trị
    @GetMapping
    public List<databin> getAllData() {
        return dataBinService.getAllData();
    }

    // API thêm giá trị mới
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_BIN')")
    public databin addDatabin(@RequestBody DataBinRequest dataBinRequest) {
        return dataBinService.addDataBin(dataBinRequest);
    }

    // API lấy giá trị gần đây nhất
    @GetMapping("/latest")
    public databin getLatestData() {
        return dataBinService.getLatestData();
    }

    @GetMapping("/{idbin}")
    public ResponseEntity<databin> getLatestDataByIdBin(@PathVariable("idbin") Long idbin) {
        databin latestData = dataBinService.getLatestDataByIdBin(idbin);
        if (latestData != null) {
            return ResponseEntity.ok(latestData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Thêm giá trị mới và kiểm tra cảnh báo mail
//    @PostMapping
//    public databin addNewDataWithMailWarn(@RequestBody DataBinRequest dataBinRequest) {
//
//        return dataBinService.addDataBin(dataBinRequest);
//    }

}

