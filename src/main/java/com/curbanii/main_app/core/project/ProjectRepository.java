package com.curbanii.main_app.core.project;

import com.curbanii.main_app.core.project.internal.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository {
    Optional<Project> findById(UUID id);
    Project save(Project project);
    void deleteById(UUID id);
    void update(Project project);
    List<Project> findAllByUserId(UUID userId);
}
