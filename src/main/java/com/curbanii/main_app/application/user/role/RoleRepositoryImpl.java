package com.curbanii.main_app.application.user.role;

import com.curbanii.main_app.core.user.role.Role;
import com.curbanii.main_app.core.user.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class RoleRepositoryImpl  implements RoleRepository {
    RoleRepositoryJpa roleRepositoryJpa;

    @Override
    public Role save(Role role) {
        return roleRepositoryJpa.save(role);
    }

    @Override
    public Role findById(UUID id) {
        return roleRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepositoryJpa.getRoleByName(name);
    }
}