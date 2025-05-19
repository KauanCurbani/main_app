package com.curbanii.main_app.application.project;

import com.curbanii.main_app.core.project.ProjectRepository;
import com.curbanii.main_app.core.project.internal.Project;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    private ProjectsRepositoryJpa projectsRepositoryJpa;

    @Override
    public Optional<Project> findById(UUID id) {
        return projectsRepositoryJpa.findById(id);
    }

    @Override
    public Project save(Project project) {
        return projectsRepositoryJpa.save(project);
    }

    @Override
    public void deleteById(UUID id) {
        projectsRepositoryJpa.deleteById(id);
    }

    @Override
    public void update(Project project) {

    }

    @Override
    public List<Project> findAllByUserId(UUID userId) {
        return projectsRepositoryJpa.findAllByUserId(userId);
    }


}
