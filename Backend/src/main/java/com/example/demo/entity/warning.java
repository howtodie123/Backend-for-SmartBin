package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warnings")

public class warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idbin", nullable = false)
    private Integer idbin;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "namebin", nullable = false)
    private String namebin;

    @Column(name = "time", nullable = false)
    private String time;
}
