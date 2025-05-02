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
@Table(name = "threshold")

    public class threshold {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "idbin", nullable = false)
        private Integer idbin;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "storage1", nullable = false)
        private Integer storage1;

        @Column(name = "storage2", nullable = false)
        private Integer storage2;

        @Column(name = "storage3", nullable = false)
        private Integer storage3;

        @Column(name = "storage4", nullable = false)
        private Integer storage4;

        @Column(name = "temperature",nullable = false)
        private Integer temperature;

    }
