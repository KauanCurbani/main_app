package com.curbanii.main_app.core.project.target;

import com.curbanii.main_app.core.project.HttpMethod;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SaveMonitorTargetDto {
    private int checkIntervalMinutes;
    private HttpMethod method;
    private String url;
}
