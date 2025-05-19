package com.curbanii.main_app.core.project.target;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MonitorTargetUseCase {
    private MonitorTargetRepository targetRepository;

    public MonitorTarget createMonitorTarget(MonitorTarget monitorTarget) {
        return targetRepository.save(monitorTarget);
    }
}
