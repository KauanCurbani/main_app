package com.curbanii.main_app.core.project.target.log;

import com.curbanii.main_app.core.project.internal.MonitorLog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MonitorLogRepository {
    MonitorLog save(MonitorLog monitorLog);
    void deleteById(UUID id);
    Optional<MonitorLog> findById(UUID id);
    List<MonitorLog> findAllByTargetId(UUID targetId);
    List<MonitorLog> findAllByTargetIdAndLimit(UUID targetId, int limit);
}
