package com.curbanii.main_app.core.project.target;

import java.util.Optional;
import java.util.UUID;

public interface MonitorTargetRepository {
    Optional<MonitorTarget> findById(UUID id);
    MonitorTarget save(MonitorTarget monitorTarget);
    void deleteById(UUID id);
}
