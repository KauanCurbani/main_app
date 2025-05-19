package com.curbanii.main_app.application.user.auth;

import com.curbanii.main_app.core.user.auth.AuthenticationUseCase;
import com.curbanii.main_app.core.user.auth.LoginRequestDto;
import com.curbanii.main_app.core.user.auth.LoginResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthenticationUseCase authenticationUseCase;

    @RequestMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequest) {
        return authenticationUseCase.loginUser(loginRequest);
    }

    @PostMapping("/register")
    public LoginResponseDto register(@RequestBody LoginRequestDto loginRequest) {
       return authenticationUseCase.register(loginRequest);
    }
}
