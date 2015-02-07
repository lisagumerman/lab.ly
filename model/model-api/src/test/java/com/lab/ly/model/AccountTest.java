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
        super(Format.Json,
                Account.class,
                User.class);
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

    @Test
    public void ensureAccountWithSingleUserAddedIsSerializedCorrectly() {
        Account account = new Account("Joe");
        User user = new User();
        user.setFirstName("Josiah");
        user.setLastName("Haswell");
        account.addUser(user);

        final Account copy = copy(account);
        assertThat(copy.getName(), is("Joe"));
        user = account.getUsers().iterator().next();
        assertThat(user.getFirstName(), is("Josiah"));
        assertThat(user.getLastName(), is("Haswell"));
        assertThat(user.getAccounts().size(), is(1));
    }

    @Test
    public void ensureAccountWithMultipleUserAddedIsSerializedCorrectly() {
        Account account = new Account();
        User user1 = new User();
        user1.setFirstName("Josiah");
        user1.setLastName("Haswell");
        user1.setEmailAddress("josiah.haswell@gmail.com");
        account.addUser(user1);

        User user2 = new User();
        user2.setFirstName("Lisa");
        user2.setLastName("Gumerman");
        user2.setEmailAddress("lisa.gumerman@gmail.com");
        account.addUser(user2);

        Account copy = copy(account);
        assertThat(copy.getUsers().size(),is(2));


        assertTrue(copy.getUsers().contains(user1));
        assertTrue(copy.getUsers().contains(user2));

    }


}