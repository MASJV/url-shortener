package com.example.url_shortener.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls", indexes = @Index(name = "idx_short_url", columnList = "shorten_url"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String longUrl;

    @Column(name = "shorten_url", unique = true)
    private String shortenUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "user_id") // nullable true by default
    private Integer userId;


}
