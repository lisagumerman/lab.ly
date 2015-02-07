package com.lab.ly.model;

import com.lab.ly.PersistenceTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.annotation.Rollback;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by haswell on 1/5/15.
 */

public class AccountPersistenceTest extends PersistenceTestCase {

    @PersistenceContext
    private EntityManager deletionEntityManager;

    @Test
    @Rollback
    public void ensureAccountCanBeSaved() {
        Account account = new Account("joe's test account");
        entityManager.persist(account);
        entityManager.flush();
        assertThat(account.getId(), is(not(nullValue())));
    }

    @Test
    @Rollback
    public void ensureAccountCanBeRetrieved() {
        Account account = new Account("joe's test account");
        entityManager.persist(account);
        entityManager.flush();
        assertThat(account.getId(), is(not(nullValue())));
        assertThat(deletionEntityManager.find(
                Account.class,
                account.getId()
        ).getName(),
                is("joe's test account"));
    }

    @Test
    public void ensureAccountCanHaveUserAdded() {
        Account account = new Account("test account");
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");
        account.addUser(user);
        entityManager.persist(account);
        assertNotNull(account.getId());
        assertNotNull(user.getId());
    }
}
