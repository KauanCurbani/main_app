package com.curbanii.main_app.core.project.target;

import com.curbanii.main_app.core.AuthenticatedUser;
import com.curbanii.main_app.core.project.ProjectRepository;
import com.curbanii.main_app.core.project.internal.MonitorTarget;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MonitorTargetUseCase {
    private MonitorTargetRepository targetRepository;
    private ProjectRepository projectRepository;
    private AuthenticatedUser authenticatedUser;

    public MonitorTargetDto createMonitorTarget(SaveMonitorTargetDto targetDto, UUID projectId) {
        if (targetDto.getCheckIntervalMinutes() < 30) {
            throw new IllegalArgumentException("Check interval must be greater than 30 minutes");
        }

        if (targetDto.getMethod() == null || targetDto.getUrl() == null) {
            throw new IllegalArgumentException("Method and URL must not be null");
        }

        if (projectRepository.findById(projectId).isEmpty()) {
            throw new IllegalArgumentException("Project not found");
        }

        MonitorTarget target = MonitorTarget.builder()
                .checkIntervalMinutes(targetDto.getCheckIntervalMinutes())
                .method(targetDto.getMethod())
                .url(targetDto.getUrl())
                .projectId(projectId)
                .build();

        return MonitorTargetDto.fromEntity(targetRepository.save(target));
    }

    public List<MonitorTargetDto> findAllByProjectId(UUID projectId) {
        if (projectRepository.findById(projectId).isEmpty()) {
            throw new IllegalArgumentException("Project not found");
        }
        if (!projectRepository.findById(projectId).get().getUserId().equals(authenticatedUser.getId())) {
            throw new IllegalArgumentException("You are not authorized to access this project");
        }

        return targetRepository.findAllByProjectId(projectId)
                .stream().map(MonitorTargetDto::fromEntity)
                .toList();
    }

    public MonitorTarget findById(UUID id) {
        MonitorTarget monitorTarget = targetRepository.findById(id).orElse(null);
        if (monitorTarget == null) throw new IllegalArgumentException("Monitor target not found");
        if (!projectRepository.findById(monitorTarget.getProjectId()).isPresent()) {
            throw new IllegalArgumentException("Project not found");
        }
        if (!projectRepository.findById(monitorTarget.getProjectId()).get().getUserId().equals(authenticatedUser.getId())) {
            throw new IllegalArgumentException("You are not authorized to access this monitor target");
        }
        return monitorTarget;
    }
}
