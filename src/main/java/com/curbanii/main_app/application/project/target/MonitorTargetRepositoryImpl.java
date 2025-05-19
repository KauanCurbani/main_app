package com.curbanii.main_app.application.project.target;

import com.curbanii.main_app.core.project.target.MonitorTarget;
import com.curbanii.main_app.core.project.target.MonitorTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MonitorTargetRepositoryImpl implements MonitorTargetRepository {
    @Autowired
    private MonitorTargetRepositoryJpa monitorTargetRepositoryJpa;

    @Override
    public MonitorTarget save(MonitorTarget monitorTarget) {
        return monitorTargetRepositoryJpa.save(monitorTarget);
    }

    @Override
    public Optional<MonitorTarget> findById(UUID id) {
        return monitorTargetRepositoryJpa.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        monitorTargetRepositoryJpa.deleteById(id);
    }
}
