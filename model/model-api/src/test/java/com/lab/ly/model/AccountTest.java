package com.lab.ly.model;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AccountTest extends SerializationTestCase {


    public AccountTest() {
        super(Account.class, Format.Json);
    }

    @Test
    public void ensureAccountsWithSameNameAreEqual() {
        final Account fst = new Account("Hello");
        assertThat(fst, is(equalTo(new Account("Hello"))));
    }

    @Test
    public void ensureAccountsWithDifferentNamesAreDifferent() {
        final Account snd = new Account("snd");
        assertThat(snd, is(not(new Account("fst"))));
    }
    
    @Test
    public void ensureAccountCanBeHashIndexedByName() {
        final Account accnt = new Account("fst");
        final Set<Account> accounts = new HashSet<>();
        assertThat(accounts.contains(accnt), is(false));
        assertTrue(accounts.add(accnt)) ;
        assertTrue(accounts.remove(new Account("fst")));
        assertFalse(accounts.contains(accnt));
    }

    @Test
    public void ensureAccountCanBeSerializedOverJson () {
        assertThat(copy(new Account("joe"), Format.Json), is(equalTo(new Account("joe"))));
    }

    @Test
    public void ensureAccountCanBeSerializedOverXml() {
        assertThat(copy(new Account("joe"), Format.Xml), is(equalTo(new Account("joe"))));
    }


}