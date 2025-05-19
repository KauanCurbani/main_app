package com.curbanii.main_app.core.user;

import com.curbanii.main_app.core.user.internal.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> getUserByUsername(String username);
    boolean existsByUsername(String username);
}