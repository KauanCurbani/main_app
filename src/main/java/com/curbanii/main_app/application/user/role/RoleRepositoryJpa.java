package com.curbanii.main_app.application.user.role;

import com.curbanii.main_app.core.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<Role, UUID> {
    Role getRoleByName(String name);
}
