package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
public class EmailRequest {
    private String to;
    private String subject;
    private String text;
    private String idbin;
}
