package com.example.url_shortener;

import com.example.url_shortener.dto.ShortenURLDTO;
import com.example.url_shortener.entity.URL;
import com.example.url_shortener.exception.URLNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor

public class UrlService {
    private final UrlRepository urlRepository;
    private static final String encoding_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 5;

    public static String base62(long id) {
        long value = id - 1; // makes first = aaaaa

        char[] result = new char[LENGTH];

        for(int i = LENGTH - 1; i >= 0; i--) {
            result[i] = encoding_chars.charAt((int) (value % 62));
            value /= 62;
        }

        return new String(result);
    }

    @Transactional(rollbackFor = Exception.class) // usage?
    public String createShortUrl(final ShortenURLDTO shortenURLDTO) {
        URL url = URL.builder()
                .longUrl(shortenURLDTO.getLongUrl())
                .createdAt(LocalDateTime.now())
                .userId(shortenURLDTO.getUserId())
                .build();

        url = urlRepository.save(url);
        log.info("Saved URL with ID: {}", url.getId());

        if (url.getId() == null) {
            throw new RuntimeException("Failed to generate ID");
        }

        // Generate short URL AFTER ID exists
        String shortenedUrl = base62(url.getId());
        log.info("Generated shortened URL: {}", shortenedUrl);

        // SECOND SAVE: update shortenUrl
        url.setShortenUrl(shortenedUrl);
        urlRepository.save(url);

        return shortenedUrl;

    }

    public String getLongUrl(String shortUrl) throws URLNotFoundException{
        final URL url = urlRepository.findByShortenUrl(shortUrl)
                .orElseThrow(() -> {
                    throw new URLNotFoundException(shortUrl);
                });

        log.info("Found long URl: {}", url.getLongUrl());
        return url.getLongUrl();
    }
}
