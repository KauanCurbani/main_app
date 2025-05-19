package com.curbanii.main_app.application.project;

import com.curbanii.main_app.core.project.internal.Project;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectsRepositoryJpa extends JpaRepository<Project, UUID> {
    Project save(Project project);

    @Query("""
            SELECT p
            FROM Project p
            WHERE p.id = :id
            AND p.deletedAt IS NULL
            """)
    Optional<Project> findById(UUID id);
    void deleteById(UUID id);
    List<Project> findAllByUserId(UUID userId);
}
