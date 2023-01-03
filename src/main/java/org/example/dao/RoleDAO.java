package org.example.dao;

import org.example.model.ERole;
import org.example.model.Role;


public interface RoleDAO {
    void create(Role role);

    Role findByName(ERole name);
}
