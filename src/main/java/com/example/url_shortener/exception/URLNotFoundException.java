package com.example.url_shortener.exception;

public class URLNotFoundException extends RuntimeException {

    public URLNotFoundException(String shortUrl) {
        super("URL not found: " + shortUrl);
    }
}
