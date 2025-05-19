package com.curbanii.main_app.application.user;

import com.curbanii.main_app.core.user.UserRepository;
import com.curbanii.main_app.core.user.internal.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepositoryJpa.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepositoryJpa.getUserByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepositoryJpa.existsByUsername(username);
    }
}
