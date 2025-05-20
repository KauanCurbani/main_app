package com.curbanii.main_app.core.project.target;

import com.curbanii.main_app.application.project.target.log.MonitorTargetLogRepoJpa;
import com.curbanii.main_app.core.project.internal.MonitorLog;
import com.curbanii.main_app.core.project.internal.MonitorTarget;
import com.curbanii.main_app.core.project.target.log.MonitorLogUseCase;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@EnableAsync
public class MonitorService {

    private final MonitorTargetRepository targetRepo;
    private final HttpChecker httpChecker;
    private final MonitorLogUseCase logUseCase;


    @Scheduled(fixedDelay = 60000)
    public void runMonitor() {
        System.out.println("Running monitor...");
        List<MonitorTarget> targets = targetRepo.findAll();
        Instant now = Instant.now();

        for (MonitorTarget target : targets) {
            if (target.getLastCheckedAt() != null) {
                long minutesSinceLastCheck = Duration.between(target.getLastCheckedAt(), now).toMinutes();
                if (minutesSinceLastCheck < target.getCheckIntervalMinutes()) continue;
            }

            checkTargetAsync(target);
        }
    }

    @Async
    public void checkTargetAsync(MonitorTarget target) {
        try {
            System.out.println("Checking target: " + target.getUrl());
            MonitorResult result = httpChecker.check(target);
            logResult(target, result);
        } catch (Exception ex) {
            logFailure(target, ex.getMessage());
        }
    }

    private void logResult(MonitorTarget target, MonitorResult result) {
        MonitorLog log = new MonitorLog();
        log.setTargetId(target.getId());
        log.setTimestamp(Instant.now());
        log.setStatusCode(result.statusCode());
        log.setIsUp(result.isUp());
        log.setResponseTimeMs(result.responseTimeMs());
        log.setErrorMessage(null);
        logUseCase.save(log);

        target.setLastStatusCode(result.statusCode());
        target.setLastIsUp(result.isUp());
        target.setLastCheckedAt(Instant.now());
        targetRepo.save(target);
    }

    private void logFailure(MonitorTarget target, String errorMsg) {
        MonitorLog log = new MonitorLog();
        log.setTargetId(target.getId());
        log.setTimestamp(Instant.now());
        log.setStatusCode(null);
        log.setIsUp(false);
        log.setResponseTimeMs(null);
        log.setErrorMessage(errorMsg);
        logUseCase.save(log);

        target.setLastStatusCode(null);
        target.setLastIsUp(false);
        target.setLastCheckedAt(Instant.now());
        targetRepo.save(target);
    }
}