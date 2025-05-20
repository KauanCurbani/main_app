package com.curbanii.main_app.application.project.target.log;

import com.curbanii.main_app.core.project.internal.MonitorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MonitorTargetLogRepoJpa extends JpaRepository<MonitorLog, UUID> {
    MonitorLog save(MonitorLog monitorLog);
    void deleteById(UUID id);
    Optional<MonitorLog> findById(UUID id);
    List<MonitorLog> findAllByTargetId(UUID targetId);


    @Query("""
            SELECT l FROM MonitorLog l
            WHERE l.targetId = :targetId
            ORDER BY l.timestamp DESC
            LIMIT :limit
            """)
    List<MonitorLog> findAllByTargetIdAndLimit(UUID targetId, int limit);
}
