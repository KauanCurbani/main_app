package com.curbanii.main_app.core.user;

import com.curbanii.main_app.application.config.exceptions.BadRequestException;
import com.curbanii.main_app.core.user.auth.LoginRequestDto;
import com.curbanii.main_app.core.user.auth.LoginResponseDto;
import com.curbanii.main_app.core.user.internal.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor

public class UserUseCase {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtEncoder jwtEncoder;

    // TODO: Move to properties
    // 24 hours
    final long EXPIRES_IN = 86400L;

    public LoginResponseDto loginUser(LoginRequestDto loginRequest) {
        User user = userRepository
                .getUserByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BadRequestException("User not found"));

        boolean isValid = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!isValid) throw new BadRequestException("Invalid password");

        String jwtValue = mountJwtToken(user);

        return LoginResponseDto.builder()
                .token(jwtValue)
                .expiration(EXPIRES_IN)
                .build();
    }

    public LoginResponseDto register(LoginRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("User already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        user = userRepository.save(user);

        String jwtToken = mountJwtToken(user);
        return LoginResponseDto.builder()
                .token(jwtToken)
                .expiration(EXPIRES_IN)
                .build();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean checkPassword(PasswordEncoder encoder, UserDto userDto, String hashedPassword) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new BadRequestException("User not found"));
        String password = user.getPassword();

        return encoder.matches(hashedPassword, password);
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found"));
        return UserDto.fromEntity(user);
    }

    private String mountJwtToken(User user) {
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