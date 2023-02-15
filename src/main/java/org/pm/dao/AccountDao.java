package org.pm.dao;

import org.hibernate.ObjectNotFoundException;
import org.pm.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;

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

    public Account findByNumber(Long number) throws NoResultException
    {
        return (Account) em.createQuery("SELECT x from Account x where x.account_number = :number")
                .setParameter("number",number)
                .getSingleResult();
    }

    public List<Account> findAll()
    {
        return em.createQuery("SELECT x from Account x", Account.class).getResultList();
    }

    public Account findByEmail(String email) throws NoResultException
    {
        return (Account) em.createQuery("SELECT x from Account x where x.account_email = :email")
                .setParameter("email",email)
                .getSingleResult();
    }

    public String generateNewPassword()
    {
        int leftLimit = 97;
        int rightLimit = 122;
        int lengthString = 10;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(lengthString)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
