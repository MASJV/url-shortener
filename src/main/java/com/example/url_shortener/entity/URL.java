package com.example.url_shortener.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String longURl;

    @Column(nullable = false, unique = true)
    private String shortenURl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long clicks;


}
