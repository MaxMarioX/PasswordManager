package org.pm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.pm.entity.Account;
import org.pm.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class LogDao {

    @PersistenceContext
    EntityManager em;

    public void save(Log log)
    {
        em.persist(log);
    }

    public List<Log> findAll()
    {
        return em
                .createQuery("select x from Log x", Log.class)
                .getResultList();
    }

    public List<Log> findLogsWithAccount(Account account)
    {
        return em
                .createQuery("select x from Log x where x.account in :account")
                .setParameter("account",account)
                .getResultList();
    }
}
