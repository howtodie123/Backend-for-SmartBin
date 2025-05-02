package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "databin")

public class databin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idbin", nullable = false)
    private Integer idbin;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private String status;

    @Min(value = 0, message = "Storage1 must be greater than or equal to 0")
    @Max(value = 100, message = "Storage1 must be less than or equal to 100")
    @Column(name = "storage1", nullable = false)
    private Integer storage1;

    @Min(value = 0, message = "Storage2 must be greater than or equal to 0")
    @Max(value = 100, message = "Storage2 must be less than or equal to 100")
    @Column(name = "storage2", nullable = false)
    private Integer storage2;

    @Min(value = 0, message = "Storage3 must be greater than or equal to 0")
    @Max(value = 100, message = "Storage3 must be less than or equal to 100")
    @Column(name = "storage3", nullable = false)
    private Integer storage3;

    @Min(value = 0, message = "Storage4 must be greater than or equal to 0")
    @Max(value = 100, message = "Storage4 must be less than or equal to 100")
    @Column(name = "storage4", nullable = false)
    private Integer storage4;

    @Min(value = 0, message = "CPU must be greater than or equal to 0")
    @Max(value = 100, message = "CPU must be less than or equal to 100")
    @Column(name = "ram",nullable = false)
    private Integer ram;

    @Min(value = 0, message = "battery must be greater than or equal to 0")
    @Max(value = 100, message = "battery must be less than or equal to 100")
    @Column(name = "temperature",nullable = false)
    private Integer temperature;

    @Column(name = "lastupdate",nullable = false)
    private String lastupdate;

}
