package com.curbanii.main_app.core.user.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleUseCase {
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(UUID id) {
        return roleRepository.findById(id);
    }

    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}