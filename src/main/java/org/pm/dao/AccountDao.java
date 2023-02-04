package org.pm.dao;

import org.pm.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return (Account) em.createQuery("SELECT x from Account x where x.account_number = :number")
                .setParameter("number",number)
                .getSingleResult();
    }

    public List<Account> findAll()
    {
        return em.createQuery("SELECT x from Account x", Account.class).getResultList();
    }
}
