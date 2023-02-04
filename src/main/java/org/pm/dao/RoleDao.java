package org.pm.dao;

import org.pm.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleDao {

    @PersistenceContext
    EntityManager em;

    public void save(Role role)
    {
        em.persist(role);
    }

    public void update(Role role)
    {
        em.merge(role);
    }

    public void remove(Role role)
    {
        em.remove(role);
    }

    public List<Role> findAll()
    {
        return em.createQuery("select x from Role x", Role.class).getResultList();
    }

    public Role findById(Long id)
    {
        return em.find(Role.class, id);
    }
}
