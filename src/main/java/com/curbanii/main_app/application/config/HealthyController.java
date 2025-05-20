package com.curbanii.main_app.application.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthy")
public class HealthyController {
    @GetMapping
    public HealthyDto healthCheck() {
        return HealthyDto.builder()
                .status("UP")
                .healthy(true)
                .build();
    }
}
