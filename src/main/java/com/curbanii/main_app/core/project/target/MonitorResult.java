package com.curbanii.main_app.core.project.target;

public record MonitorResult(
    int statusCode,
    boolean isUp,
    long responseTimeMs
) {}