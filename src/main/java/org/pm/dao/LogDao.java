package org.pm.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.pm.entity.Account;
import org.pm.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class LogDao {

    EntityManager em;

    public void save(Log log)
    {
        em.persist(log);
    }

    public void update(Log log)
    {
        em.merge(log);
    }

    public void remove(Log log)
    {
        em.remove(log);
    }

    public List<Log> findLogsWithAccount(Account account)
    {
        return em
                .createQuery("select x from Log x where x.account in :account")
                .setParameter("account",account)
                .getResultList();
    }
}
