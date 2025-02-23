package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ThresholdUpdateRequest {
    private Integer storage1;
    private Integer storage2;
    private Integer storage3;
    private Integer storage4;
    private Integer battery;
}
