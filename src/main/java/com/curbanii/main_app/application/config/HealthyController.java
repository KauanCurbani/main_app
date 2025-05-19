package com.curbanii.main_app.application.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/healthy")
public class HealthyController {
    @GetMapping
    public Object healthCheck() {
        return new Object() {
            public String status = "UP";
            public boolean healthy = true;
        };
    }
}
