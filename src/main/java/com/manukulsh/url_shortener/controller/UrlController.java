
package com.manukulsh.url_shortener.controller;

import com.manukulsh.url_shortener.dto.CreateUrlRequest;
import com.manukulsh.url_shortener.dto.UrlResponse;
import com.manukulsh.url_shortener.entity.Url;
import com.manukulsh.url_shortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/urls")
    public UrlResponse createShortUrl(
            @Valid @RequestBody CreateUrlRequest request) {

        Url url = urlService.createShortUrl(request.getOriginalUrl());

        String shortUrl = "http://localhost:8080/" + url.getShortCode();

        return new UrlResponse(
                url.getOriginalUrl(),
                url.getShortCode(),
                shortUrl
        );
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(
            @PathVariable String shortCode) {

        Url url = urlService.getByShortCode(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, url.getOriginalUrl())
                .build();
    }
}
