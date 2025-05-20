package com.curbanii.main_app.application.project.target;

import com.curbanii.main_app.core.project.internal.MonitorTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MonitorTargetRepositoryJpa extends JpaRepository<MonitorTarget,UUID> {
    MonitorTarget save(MonitorTarget monitorTarget);
    void deleteById(UUID id);
    Optional<MonitorTarget> findById(UUID id);
    List<MonitorTarget> findAllByProjectId(UUID projectId);
    List<MonitorTarget> findAll();
}
