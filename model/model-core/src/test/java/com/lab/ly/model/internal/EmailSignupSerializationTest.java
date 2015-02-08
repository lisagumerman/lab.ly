package com.lab.ly.model.internal;

import com.lab.ly.model.SerializationTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EmailSignupSerializationTest extends SerializationTestCase {

    public EmailSignupSerializationTest() {
        super(Format.Json, EmailSignup.class);
    }

    @Test
    public void ensureSignupCanBeSerializedToJson() {
        final EmailSignup signup = new EmailSignup();
        signup.setEmailAddress("josiah.haswell@gmail.com");
        final EmailSignup copy = copy(signup);
        assertThat(copy, is(equalTo(signup)));
    }
}