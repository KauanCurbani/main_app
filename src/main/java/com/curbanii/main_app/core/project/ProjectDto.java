package com.curbanii.main_app.core.project;

import com.curbanii.main_app.core.project.internal.Project;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class ProjectDto {
    private UUID id;
    private String name;
    private Instant createdAt;

    static ProjectDto fromEntity(Project project) {
        if (project == null) return null;
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .createdAt(project.getCreatedAt())
                .build();
    }
}
