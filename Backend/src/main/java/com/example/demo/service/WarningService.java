package com.example.demo.service;

import com.example.demo.entity.warning;

import java.util.List;

public interface WarningService {
    // Lấy tất cả cảnh báo
    List<warning> getAllWarnings();

    // Lấy cảnh báo theo idbin
    List<warning> getWarningsByIdBin(Integer idbin);

    //thêm Warning mới
    warning createWarning(warning warning);

    // Xóa cảnh báo theo id
    String deleteWarningById(Integer id);

    // Xóa toàn bộ cảnh báo
    String deleteAllWarnings();
}
