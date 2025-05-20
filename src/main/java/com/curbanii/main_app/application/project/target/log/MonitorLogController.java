package com.curbanii.main_app.application.project.target.log;

import com.curbanii.main_app.core.project.internal.MonitorLog;
import com.curbanii.main_app.core.project.target.log.MonitorLogUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/{targetId}/logs")
@AllArgsConstructor
public class MonitorLogController {

    private final MonitorLogUseCase monitorLogUseCase;

    @GetMapping
    public List<MonitorLog> getAllLogsByTargetId(@PathVariable UUID targetId, @RequestParam(required = false) Integer limit) {
        return monitorLogUseCase.findAllByTargetIdAndLimit(targetId, Objects.requireNonNullElse(limit, 15));
    }
}
