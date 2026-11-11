package com.manukulsh.url_shortener.dto;

public class CreateUrlRequest {

    private String originalUrl;

    public CreateUrlRequest() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
