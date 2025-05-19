package com.curbanii.main_app.core.project.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "monitor_logs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant timestamp = Instant.now();
    private Integer statusCode;
    private Boolean isUp;
    private Long responseTimeMs;
    private String errorMessage;
    private UUID targetId;
}