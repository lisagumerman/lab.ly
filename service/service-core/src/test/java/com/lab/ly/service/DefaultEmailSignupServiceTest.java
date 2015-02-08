package com.lab.ly.service;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DefaultEmailSignupServiceTest extends ServiceTestCase {

    @Inject
    private EmailSignupService service;

    @Test
    @Rollback
    public void ensureCanSaveValidEmailAddress() {
        String result = service.save("josiah.haswell@gmail.com");
        assertThat(result.contains("Success"), is(true));
    }

    @Test
    public void ensureServiceListsSignupsCorrectly() {
        service.save("one@gmail.com");
        service.save("two@gmail.com");
        assertThat(service.getSignups().size(), is(2));
    }

}