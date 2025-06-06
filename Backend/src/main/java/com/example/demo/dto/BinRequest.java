package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BinRequest {
    private Integer id;
    private String name;
    private String locationX;
    private String locationY;
}
