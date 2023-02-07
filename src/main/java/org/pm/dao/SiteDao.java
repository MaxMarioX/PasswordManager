package org.pm.dao;

import org.pm.entity.Account;
import org.pm.entity.Site;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    public Site findSiteById(Long Id)
    {
        return (Site) em
                .createQuery("select x from Site x where x.id=:site_id")
                .setParameter("site_id",Id)
                .getSingleResult();

    }

}
