package com.help.controller;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpController {

    private final ResourceLoader resourceLoader;

    public HelpController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/help/{type}")
    public ResponseEntity<Resource> getHelpContent(@PathVariable String type) {
        String resourcePath;
        String contentType;

        if ("video".equalsIgnoreCase(type)) {
            resourcePath = "classpath:videos/help-video.mp4"; 
            contentType = "video/mp4";
        } else if ("image".equalsIgnoreCase(type)) {
            resourcePath = "classpath:images/help-image.jpg";
            contentType = "image/jpeg"; 
        } else {
            return ResponseEntity.badRequest().build();
        }

        Resource resource = resourceLoader.getResource(resourcePath);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}