package com.curbanii.main_app.core.user.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
    private long expiration;
}
