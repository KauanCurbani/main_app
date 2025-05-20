package com.curbanii.main_app.core.project;

import com.curbanii.main_app.core.AuthenticatedUser;
import com.curbanii.main_app.core.project.internal.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjectUseCase {

    private final ProjectRepository projectRepository;
    private AuthenticatedUser authenticatedUser;

    public Optional<ProjectDto> findById(UUID id) {
        return projectRepository.findById(id).map(ProjectDto::fromEntity);
    }

    public ProjectDto save(ProjectSaveDto saveProductDto) {
        Project project = Project.builder()
                .name(saveProductDto.getName())
                .userId(authenticatedUser.getId())
                .build();
        return ProjectDto.fromEntity(projectRepository.save(project));
    }

    public void deleteById(UUID id) {
        if (projectRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Project not found");
        }
        if (!projectRepository.findById(id).get().getUserId().equals(authenticatedUser.getId())) {
            throw new IllegalArgumentException("You are not authorized to delete this project");
        }

        projectRepository.deleteById(id);
    }

    public List<ProjectDto> findAllByUserId() {
        return projectRepository.findAllByUserId(authenticatedUser.getId())
                .stream().map(ProjectDto::fromEntity)
                .toList();
    }
}
