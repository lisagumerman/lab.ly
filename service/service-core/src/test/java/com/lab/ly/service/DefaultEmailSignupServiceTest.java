package com.lab.ly.service;

import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DefaultEmailSignupServiceTest extends ServiceTestCase {

    @Inject
    private EmailSignupService service;

    @Test
    public void ensureCanSaveValidEmailAddress() {
        String result = service.save("josiah.haswell@gmail.com");
        assertThat(result.contains("Success"), is(true));
    }

}