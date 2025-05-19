package com.curbanii.main_app.application.project;

import com.curbanii.main_app.core.project.ProjectDto;
import com.curbanii.main_app.core.project.ProjectUseCase;
import com.curbanii.main_app.core.project.ProjectSaveDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {
    private final ProjectUseCase projectUseCase;

    @GetMapping("/{id}")
    public Optional<ProjectDto> getProjectById(@PathVariable UUID id) {
        return projectUseCase.findById(id);
    }

    @PostMapping
    public ProjectDto createProject(@RequestBody ProjectSaveDto project) {
        return projectUseCase.save(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable UUID id) {
        projectUseCase.deleteById(id);
    }

    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectUseCase.findAllByUserId();
    }
}
