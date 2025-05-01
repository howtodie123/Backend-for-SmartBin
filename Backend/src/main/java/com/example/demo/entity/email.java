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
@Table(name = "emails")
public class email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "receiver", nullable = false)
    private String receiver;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "idbin")
    private String idbin;

}
