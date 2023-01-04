package org.example.dao.impl;

import org.example.dao.RoleDAO;
import org.example.model.ERole;
import org.example.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void create(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
    }

    @Override
    public Role findByName(ERole name) {
        Session session = sessionFactory.getCurrentSession();
        Role role = (Role) session
                .createQuery("from Role where name = :name ")
                .setParameter("name",name)
                .uniqueResult();
        return role;
    }
}
