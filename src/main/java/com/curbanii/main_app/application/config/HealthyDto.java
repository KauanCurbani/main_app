package com.curbanii.main_app.application.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HealthyDto {
    private String status;
    private boolean healthy;
}
