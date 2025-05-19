package com.curbanii.main_app.core.user;

import com.curbanii.main_app.core.user.internal.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String username;
    private boolean enabled;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .enabled(user.isEnabled())
                .build();
    }

    public static UserDto fromObject(Object object) {
        return UserDto.builder()
                .id(UUID.fromString(object.toString()))
                .build();
    }
}