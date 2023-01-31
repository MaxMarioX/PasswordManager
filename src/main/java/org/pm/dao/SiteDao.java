package org.pm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.pm.entity.Account;
import org.pm.entity.Site;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class SiteDao {

    @PersistenceContext
    EntityManager em;

    public void save(Site site)
    {
        em.persist(site);
    }

    public void update(Site site)
    {
        em.merge(site);
    }

    public void remove(Site site)
    {
        em.remove(site);
    }

    public List<Site> findSitesWithAccount(Account account) {
        return em
                .createQuery("select x from Site x where x.account in :account")
                .setParameter("account", account)
                .getResultList();

    }

}
