package com.curbanii.main_app.core.project.target;

import com.curbanii.main_app.core.project.internal.MonitorTarget;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class MonitorTargetDto {
    private UUID id;
    private String url;
    private String method;
    private int checkIntervalMinutes;
    private Integer lastStatusCode;
    private Boolean lastIsUp;
    private Instant lastCheckedAt;
    private Instant createdAt;

    static MonitorTargetDto fromEntity(MonitorTarget monitorTarget) {
        if (monitorTarget == null) return null;
        return MonitorTargetDto.builder()
                .id(monitorTarget.getId())
                .url(monitorTarget.getUrl())
                .method(monitorTarget.getMethod().name())
                .checkIntervalMinutes(monitorTarget.getCheckIntervalMinutes())
                .lastStatusCode(monitorTarget.getLastStatusCode())
                .lastIsUp(monitorTarget.getLastIsUp())
                .lastCheckedAt(monitorTarget.getLastCheckedAt())
                .createdAt(monitorTarget.getCreatedAt())
                .build();
    }
}
