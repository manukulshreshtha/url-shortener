package com.manukulsh.url_shortener.controller;

import com.manukulsh.url_shortener.dto.CreateUrlRequest;
import com.manukulsh.url_shortener.dto.UrlResponse;
import com.manukulsh.url_shortener.entity.Url;
import com.manukulsh.url_shortener.service.UrlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public UrlResponse createShortUrl(@RequestBody CreateUrlRequest request) {

        Url url = urlService.createShortUrl(request.getOriginalUrl());

        String shortUrl = "http://localhost:8080/" + url.getShortCode();

        return new UrlResponse(
                url.getOriginalUrl(),
                url.getShortCode(),
                shortUrl
        );
    }
}
