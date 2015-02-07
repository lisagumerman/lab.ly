package com.lab.ly.model.internal;

import com.lab.ly.PersistenceTestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailSignupTest extends PersistenceTestCase {

    @Test
    public void ensureEmailAddressCanBeSaved() {
        EmailSignup signup = new EmailSignup();
        signup.setEmailAddress("Josiah.Haswell@gmail.com");
        entityManager.persist(signup);
        assertNotNull(signup);
    }

}