package com.lab.ly.model;

import com.lab.ly.PersistenceTestCase;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertNotNull;

/**
 * Created by haswell on 2/2/15.
 */
public class UserPersistenceTest extends PersistenceTestCase {

    @Test
    @Rollback
    public void ensureUserWithEmailAddressAndNoFirstOrLastNameCanBeSaved() {
        User user = new User();
        user.setEmailAddress("josiah@lably.io");
        entityManager.persist(user);
        assertNotNull(user.getId());
    }


}
