package com.example.url_shortener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")

@Slf4j
@RequiredArgsConstructor

public class UrlController {
    private final UrlService urlService;

    @PostMapping("/")
    public ResponseEntity<String> createShortURL(@RequestBody ShortenURLDTO shortenURLDTO) {
        log.info("received a request to create short url");
        try {
            return ResponseEntity.ok("Demo link");
        }
        catch(Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/{url}")
    public ResponseEntity<String> fetchLongURL(@PathVariable("url") String url) {
        log.info("received a request to get long url with url {}", url);
        try {
            return ResponseEntity.ok(url);
        }
        catch(Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

}
