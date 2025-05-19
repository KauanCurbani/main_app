package com.curbanii.main_app.core.project.target;

import com.curbanii.main_app.core.project.HttpMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "monitor_targets")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MonitorTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String url;
    @Enumerated(EnumType.STRING)
    private HttpMethod method = HttpMethod.GET;
    private int checkIntervalMinutes = 5;
    private String expectedKeyword;
    private Integer lastStatusCode;
    private Boolean lastIsUp;
    private Instant lastCheckedAt;
    private UUID projectId;

    @SoftDelete
    private Instant deletedAt;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

}