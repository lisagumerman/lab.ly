package com.lab.ly.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 7:02 PM
 */
public class HeaderSetSerializationTest extends SerializationTestCase {

    public HeaderSetSerializationTest() {
        super(HeaderSet.class);
    }

    @Test
    public void ensureHeaderSetIsSerializedCorrectly() {
        final HeaderSet set = new HeaderSet();
        set.setHeaders(new HashSet<>(
                Arrays.asList("one", "two", "three")));

        assertThat(set, is(copy(set)));
    }

}
