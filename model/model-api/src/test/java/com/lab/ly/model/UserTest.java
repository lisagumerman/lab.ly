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

    @Test
    public void ensureUsersWithSameEmailAreEqual() {
        final User user = new User();
        user.setFirstName("Josiah");
        user.setLastName("Haswell");
        user.setEmailAddress("josiah@lably.io");

        final User user2 = new User();
        user2.setFirstName("Lisa");
        user2.setLastName("Gumerman");
        user2.setEmailAddress("josiah@lably.io");
        assertThat(user, is(user2));
    }

}
