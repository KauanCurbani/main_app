package com.curbanii.main_app.core.project.target;

import com.curbanii.main_app.core.project.internal.MonitorTarget;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class HttpChecker {

    public MonitorResult check(MonitorTarget target) {
        long start = System.currentTimeMillis();

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(target.getUrl()).openConnection();
            conn.setRequestMethod(target.getMethod().name());
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int statusCode = conn.getResponseCode();
            String body = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

            boolean keywordOk = target.getExpectedKeyword() == null ||
                    body.contains(target.getExpectedKeyword());

            boolean isUp = statusCode >= 200 && statusCode < 400 && keywordOk;
            long responseTime = System.currentTimeMillis() - start;

            return new MonitorResult(statusCode, isUp, responseTime);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao checar: " + e.getMessage());
        }
    }
}