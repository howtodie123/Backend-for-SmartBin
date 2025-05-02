package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class DataBinRequest {

    private String name;

    private Integer idbin;

    private String status;

    @Min(value = 0, message = "Storage1 must be greater than or equal to 0")
    @Max(value = 100, message = "Storage1 must be less than or equal to 100")
    private Integer storage1;

    @Min(value = 0, message = "Storage2 must be greater than or equal to 0")
    @Max(value = 100, message = "Storage2 must be less than or equal to 100")
    private Integer storage2;

    @Min(value = 0, message = "Storage3 must be greater than or equal to 0")
    @Max(value = 100, message = "Storage3 must be less than or equal to 100")
    private Integer storage3;

    @Min(value = 0, message = "Storage4 must be greater than or equal to 0")
    @Max(value = 100, message = "Storage4 must be less than or equal to 100")
    private Integer storage4;

    @Min(value = 0, message = "RAM must be greater than or equal to 0")
    @Max(value = 100, message = "RAM must be less than or equal to 100")
    private Integer ram;

    @Min(value = 0, message = "temperature must be greater than or equal to 0")
    @Max(value = 100, message = "temperature must be less than or equal to 100")
    private Integer temperature;

    private String lastupdate;
}

