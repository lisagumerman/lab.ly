package com.lab.ly.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by haswell on 1/5/15.
 */

@RunWith(Suite.class)
public class AccountPersistenceTest {

    @Inject
    private EntityManager entityManager;


    @Test
    public void ensureAccountCanBeSaved() {
        Account account = new Account("joe's test account");

    }
}
