package org.pm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.pm.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AccountDao {
    @PersistenceContext
    EntityManager em;

    public void save(Account entity)
    {
        em.persist(entity);
    }

    public void update(Account entity)
    {
        em.merge(entity);
    }

    public void delete(Account entity)
    {
        em.remove(entity);
    }

    public Account findById(Long id)
    {
        return em.find(Account.class, id);
    }

    public Account findByNumber(Long number)
    {
        return (Account) em.createQuery("SELECT x from Account x where x.account_number = :numer")
                .setParameter("number",number)
                .getSingleResult();
    }

    public List<Account> findAll()
    {
        return em.createQuery("SELECT x from Account x", Account.class).getResultList();
    }
}
