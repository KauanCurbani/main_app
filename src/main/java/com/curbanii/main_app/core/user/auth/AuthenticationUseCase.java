package com.curbanii.main_app.core.user.auth;

import com.curbanii.main_app.application.config.exceptions.BadRequestException;
import com.curbanii.main_app.core.user.UserDto;
import com.curbanii.main_app.core.user.UserUseCase;
import com.curbanii.main_app.core.user.internal.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthenticationUseCase {
    private UserUseCase userUseCase;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtEncoder jwtEncoder;

    final long EXPIRES_IN = 86400L;

    public LoginResponseDto loginUser(LoginRequestDto loginRequest) {
        UserDto user = userUseCase.getUserByUsername(loginRequest.getUsername());

        boolean isValid = userUseCase.checkPassword(passwordEncoder, user, loginRequest.getPassword());
        if (!isValid) throw new BadRequestException("Invalid password");

        String jwtValue = mountJwtToken(user);

        return LoginResponseDto.builder()
                .token(jwtValue)
                .expiration(EXPIRES_IN)
                .build();
    }

    public LoginResponseDto register(LoginRequestDto request) {
        if (userUseCase.existsByUsername(request.getUsername())) {
            throw new BadRequestException("User already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        user = userUseCase.save(user);

        String jwtToken = mountJwtToken(UserDto.fromEntity(user));
        return LoginResponseDto.builder()
                .token(jwtToken)
                .expiration(EXPIRES_IN)
                .build();
    }

    private String mountJwtToken(UserDto user) {
        Instant now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .issuer("https://api.curbanii.net")
                .subject(user.getId().toString())
                .claims(c -> c.put("id", user.getId()))
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRES_IN))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
