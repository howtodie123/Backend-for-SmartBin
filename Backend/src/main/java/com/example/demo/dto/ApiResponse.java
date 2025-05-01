package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
@AllArgsConstructor
@Data
@Builder
public class ApiResponse<T> {
    private String status;
    private String message;
    private T response;
}
