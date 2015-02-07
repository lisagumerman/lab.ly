package com.lab.ly.service;

import com.lab.ly.AccountService;
import com.lab.ly.model.Account;
import com.lab.ly.model.User;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by haswell on 2/2/15.
 */
public class AccountServiceTest extends ServiceTestCase {

    @Inject
    private AccountService service;

    @Test
    public void ensureServiceCanAddAccount() {
        final Account account = new Account();
        account.setName("new users");
        Long id = service.save(account);
        assertNotNull(id);
    }

    @Test
    public void ensureServiceCanAddUserToAccount() {
        final Account account = new Account();
        account.setName("new users");
        Long id = service.save(account);
        User user = new User();
        user.setEmailAddress("josiah@lably.io");
        user = service.addUser(id, user);
        assertThat(user.getId(),is(not(nullValue())));

    }

}
