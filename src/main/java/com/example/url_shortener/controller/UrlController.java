package com.example.url_shortener.controller;

import com.example.url_shortener.dto.ShortenURLDTO;
import com.example.url_shortener.UrlService;
import com.example.url_shortener.exception.URLNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/urls")
@Slf4j
@RequiredArgsConstructor

public class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> createShortURL(@RequestBody ShortenURLDTO shortenURLDTO) {
        log.info("Received request to shorten URL: {}", shortenURLDTO);
        try {
            final String shortenedUrl = urlService.createShortUrl(shortenURLDTO);
            return ResponseEntity.ok(shortenedUrl);
        }
        catch(Exception e) {
            log.error("ERROR OCCURRED:", e);
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }


    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> fetchLongURL(@PathVariable("shortUrl") String shortUrl) {
        log.info("received a request to get long url with url {}", shortUrl);

        try {
            final String longUrl = urlService.getLongUrl(shortUrl);
            return ResponseEntity
                    .status(HttpStatus.PERMANENT_REDIRECT)
                    .location(URI.create(longUrl))
                    .build();
        }
        catch(URLNotFoundException e) {
            return ResponseEntity.status(404).body("URL not found");
        }
        catch(Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    //analytics to be added

}
