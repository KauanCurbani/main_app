package com.curbanii.main_app.application.project.target;

import com.curbanii.main_app.core.project.internal.MonitorTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MonitorTargetRepositoryJpa extends JpaRepository<MonitorTarget,UUID> {
    MonitorTarget save(MonitorTarget monitorTarget);
    void deleteById(UUID id);
    Optional<MonitorTarget> findById(UUID id);

    @Query("""
            SELECT t FROM MonitorTarget t
            WHERE t.projectId = :projectId
            ORDER BY t.createdAt DESC
            """)
    List<MonitorTarget> findAllByProjectId(UUID projectId);
    List<MonitorTarget> findAll();
}
