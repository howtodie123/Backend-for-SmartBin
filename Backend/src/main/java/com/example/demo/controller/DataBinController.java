package com.example.demo.controller;

import com.example.demo.dto.DataBinRequest;
import com.example.demo.entity.databin;
import com.example.demo.service.DataBinService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.demo.service.EmailService;


import java.util.List;

@RestController
@RequestMapping("/api/databins")
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:63342/")
=======
@CrossOrigin(origins = "http://localhost:63342")
>>>>>>> ca8492138dd65490aacc75e7e394a40feee64289
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
    public databin addDatabin(@RequestBody DataBinRequest dataBinRequest) {
        return dataBinService.addDataBin(dataBinRequest);
    }

    // API lấy giá trị gần đây nhất
    @GetMapping("/latest")
    public databin getLatestData() {
        return dataBinService.getLatestData();
    }

    // Thêm giá trị mới và kiểm tra cảnh báo mail
//    @PostMapping
//    public databin addNewDataWithMailWarn(@RequestBody DataBinRequest dataBinRequest) {
//
//        return dataBinService.addDataBin(dataBinRequest);
//    }

}

