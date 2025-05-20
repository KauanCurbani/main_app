package com.curbanii.main_app.application.project.target;

import com.curbanii.main_app.core.project.internal.MonitorTarget;
import com.curbanii.main_app.core.project.target.MonitorTargetDto;
import com.curbanii.main_app.core.project.target.MonitorTargetUseCase;
import com.curbanii.main_app.core.project.target.SaveMonitorTargetDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project/{projectId}/target")
@AllArgsConstructor
public class MonitorTargetController {

    @Autowired
    private final MonitorTargetUseCase monitorTargetUseCase;

    @PostMapping
    public MonitorTargetDto createMonitorTarget(@PathVariable UUID projectId, @RequestBody SaveMonitorTargetDto targetDto) {
        return monitorTargetUseCase.createMonitorTarget(targetDto, projectId);
    }

    @GetMapping
    public List<MonitorTargetDto> getAllMonitorTargets(@PathVariable UUID projectId) {
        return monitorTargetUseCase.findAllByProjectId(projectId);
    }
}
