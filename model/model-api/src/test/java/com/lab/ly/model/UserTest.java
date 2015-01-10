package com.lab.ly.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by haswell on 1/10/15.
 */
public class UserTest extends SerializationTestCase {
    public UserTest() {
        super(Format.Json, User.class, Account.class);
    }

    @Test
    public void ensureUserFirstNameIsSerializedCorrectly() {
        final User user = new User();
        user.setFirstName("Josiah");
        final User copy = copy(user);
        assertThat(copy.getFirstName(), is("Josiah"));
    }

    @Test
    public void ensureLastNameIsSerializedCorrectly() {
        final User user = new User();
        user.setLastName("test");
        assertThat(copy(user).getLastName(), is("test"));
    }

    @Test
    public void ensureAccountsAreSerializedCorrectly() {
        final Account account = new Account("test");
        final User user = new User();
        user.setFirstName("Josiah");
        account.addUser(user);
        assertThat(copy(account).getUsers().size(), is(1));
    }

}
