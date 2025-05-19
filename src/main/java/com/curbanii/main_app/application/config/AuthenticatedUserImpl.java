package com.curbanii.main_app.application.config;

import com.curbanii.main_app.core.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticatedUserImpl implements AuthenticatedUser {

    @Override
    public UUID getId() {
        String subject = getSubjectFromJwtToken();
        if (subject == null) return null;
        return UUID.fromString(subject);
    }

    private String getSubjectFromJwtToken() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}