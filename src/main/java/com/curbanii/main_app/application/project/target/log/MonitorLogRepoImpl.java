package com.curbanii.main_app.application.project.target.log;

import com.curbanii.main_app.core.project.internal.MonitorLog;
import com.curbanii.main_app.core.project.target.log.MonitorLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class MonitorLogRepoImpl implements MonitorLogRepository {

    private final MonitorTargetLogRepoJpa monitorTargetLogRepoJpa;

    @Override
    public MonitorLog save(MonitorLog monitorLog) {
        return monitorTargetLogRepoJpa.save(monitorLog);
    }

    @Override
    public void deleteById(UUID id) {
        monitorTargetLogRepoJpa.deleteById(id);
    }

    @Override
    public Optional<MonitorLog> findById(UUID id) {
        return monitorTargetLogRepoJpa.findById(id);
    }

    @Override
    public List<MonitorLog> findAllByTargetId(UUID targetId) {
        return monitorTargetLogRepoJpa.findAllByTargetId(targetId);
    }

    @Override
    public List<MonitorLog> findAllByTargetIdAndLimit(UUID targetId, int limit) {
        return monitorTargetLogRepoJpa.findAllByTargetIdAndLimit(targetId, limit);
    }
}
