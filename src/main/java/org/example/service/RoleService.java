package org.example.service;

import org.example.model.ERole;
import org.example.model.Role;

public interface RoleService {
    void create(Role role);

    Role find(ERole name);
}
