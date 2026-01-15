package com.example.url_shortener;

import com.example.url_shortener.entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<URL, Long> {
    Optional<URL> findByShortenUrl(String shortenUrl);
}
