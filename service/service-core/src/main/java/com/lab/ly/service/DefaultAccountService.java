package com.lab.ly.service;

import com.lab.ly.AccountService;
import com.lab.ly.model.Account;
import com.lab.ly.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 * Created by haswell on 2/2/15.
 */

@Service
@Transactional
public class DefaultAccountService implements AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User addUser(Long accountId, User user) {
        final Account account = entityManager.find(Account.class, accountId);
        if(account == null) {
            throw new EntityNotFoundException("Account identified by " +
                    accountId + " could not be found");
        }
        account.addUser(user);
        entityManager.flush();
        return user;
    }

    @Override
    public Long save(Account account) {
        entityManager.persist(account);
        return account.getId();
    }
}
