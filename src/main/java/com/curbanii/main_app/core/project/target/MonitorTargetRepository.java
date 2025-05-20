package com.curbanii.main_app.core.project.target;

import com.curbanii.main_app.core.project.internal.MonitorTarget;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MonitorTargetRepository {
    Optional<MonitorTarget> findById(UUID id);
    MonitorTarget save(MonitorTarget monitorTarget);
    void deleteById(UUID id);
    List<MonitorTarget> findAllByProjectId(UUID projectId);
    List<MonitorTarget> findAll();
}
