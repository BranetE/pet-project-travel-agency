package org.example.service.impl;

import org.example.dao.RoleDAO;
import org.example.exception.NullEntityReferenceException;
import org.example.model.ERole;
import org.example.model.Role;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;
    @Override
    public void create(Role role) throws NullEntityReferenceException {
        if(role == null){
            throw new NullEntityReferenceException("Entity role is null in create method");
        }
        roleDAO.create(role);
    }

    @Override
    public Role find(ERole name) {
        Role role = roleDAO.findByName(name);
        return role;
    }
}
