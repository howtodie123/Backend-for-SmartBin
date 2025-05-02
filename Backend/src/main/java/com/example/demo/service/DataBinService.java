package com.example.demo.service;

import com.example.demo.dto.DataBinRequest;
import com.example.demo.entity.databin;

import java.util.*;


public interface DataBinService {
    List<databin> getAllData();
    databin addDataBin(DataBinRequest dataBinRequest);
    databin getLatestData();
    databin getLatestDataByIdBin(Long idbin);
}
