package com.curbanii.main_app.core.project.target;

import com.curbanii.main_app.core.project.internal.MonitorTarget;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MonitorTargetUseCase {
    private MonitorTargetRepository targetRepository;

    public MonitorTargetDto createMonitorTarget(SaveMonitorTargetDto targetDto, UUID projectId) {
        MonitorTarget target = MonitorTarget.builder()
                .checkIntervalMinutes(targetDto.getCheckIntervalMinutes())
                .method(targetDto.getMethod())
                .url(targetDto.getUrl())
                .projectId(projectId)
                .build();

        return MonitorTargetDto.fromEntity(targetRepository.save(target));
    }

    public List<MonitorTargetDto> findAllByProjectId(UUID projectId) {
        return targetRepository.findAllByProjectId(projectId)
                .stream().map(MonitorTargetDto::fromEntity)
                .toList();
    }

    public MonitorTarget findById(UUID id) {
        return targetRepository.findById(id).orElse(null);

    }
}
