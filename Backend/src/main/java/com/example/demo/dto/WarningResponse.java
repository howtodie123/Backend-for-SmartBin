package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class WarningResponse {

    private String idbin;
    private String message;
    private String namebin;
    private String time;
}
