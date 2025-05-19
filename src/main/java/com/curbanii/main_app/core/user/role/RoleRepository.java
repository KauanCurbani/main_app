package com.curbanii.main_app.core.user.role;

import java.util.UUID;

public interface RoleRepository {
    Role save(Role role);
    Role findById(UUID id);
    Role getRoleByName(String name);
}