package com.curbanii.main_app.application.user;

import com.curbanii.main_app.core.user.internal.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, UUID> {
    Optional<User> getUserByUsername(String username);

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findById(UUID id);

    boolean existsByUsername(String username);
}
