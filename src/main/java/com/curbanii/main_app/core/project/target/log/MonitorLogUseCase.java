package com.curbanii.main_app.core.project.target.log;

import com.curbanii.main_app.application.config.exceptions.BadRequestException;
import com.curbanii.main_app.core.AuthenticatedUser;
import com.curbanii.main_app.core.project.ProjectRepository;
import com.curbanii.main_app.core.project.internal.MonitorLog;
import com.curbanii.main_app.core.project.internal.MonitorTarget;
import com.curbanii.main_app.core.project.internal.Project;
import com.curbanii.main_app.core.project.target.MonitorTargetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MonitorLogUseCase {

    private final MonitorLogRepository monitorLogRepository;
    private final MonitorTargetRepository targetRepository;
    private final ProjectRepository projectRepository;
    private final AuthenticatedUser authenticatedUser;

    public MonitorLog save(MonitorLog monitorLog) {
        MonitorTarget monitorTarget = targetRepository.findById(monitorLog.getTargetId()).orElse(null);
        if (monitorTarget == null) throw new BadRequestException("Monitor target not found");
        Project project = projectRepository.findById(monitorTarget.getProjectId()).orElse(null);
        if (project == null) throw new BadRequestException("Project not found");
        if (!project.getUserId().equals(authenticatedUser.getId())) {
            throw new BadRequestException("You are not authorized to access this monitor log");
        }
        return monitorLogRepository.save(monitorLog);
    }

    public void deleteById(UUID id) {
        MonitorLog monitorLog = monitorLogRepository.findById(id).orElse(null);
        if (monitorLog == null) throw new BadRequestException("Monitor log not found");
        checkProjectOwnership(monitorLog);

        monitorLogRepository.deleteById(id);
    }

    public MonitorLog findById(UUID id) {
        MonitorLog monitorLog = monitorLogRepository.findById(id).orElse(null);
        if (monitorLog == null) throw new BadRequestException("Monitor log not found");
        checkProjectOwnership(monitorLog);

        return monitorLogRepository.findById(id).orElse(null);
    }

    public List<MonitorLog> findAllByTargetId(UUID targetId) {

        return monitorLogRepository.findAllByTargetId(targetId);
    }

    public List<MonitorLog> findAllByTargetIdAndLimit(UUID targetId, int limit) {
        MonitorTarget monitorTarget = targetRepository.findById(targetId).orElse(null);
        if (monitorTarget == null) throw new BadRequestException("Monitor target not found");
        Project project = projectRepository.findById(monitorTarget.getProjectId()).orElse(null);

        return monitorLogRepository.findAllByTargetIdAndLimit(targetId, limit);
    }

    private void checkProjectOwnership(MonitorLog monitorLog) {
        Optional<MonitorTarget> target = targetRepository.findById(monitorLog.getTargetId());
        if (target.isEmpty()) throw new BadRequestException("Monitor target not found");
        Project project = projectRepository.findById(target.get().getProjectId()).orElse(null);
        if (project == null) throw new BadRequestException("Project not found");
        if (!project.getUserId().equals(authenticatedUser.getId())) {
            throw new BadRequestException("You are not authorized to access this monitor log");
        }
    }
}
