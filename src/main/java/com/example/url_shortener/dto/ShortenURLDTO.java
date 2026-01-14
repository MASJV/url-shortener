package com.example.url_shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ShortenURLDTO {
    private String longUrl;
    private Integer userId;

}
