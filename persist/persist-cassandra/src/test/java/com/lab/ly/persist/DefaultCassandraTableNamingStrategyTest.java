package com.lab.ly.persist;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultCassandraTableNamingStrategyTest {

    final NamingStrategy strategy = new DefaultCassandraTableNamingStrategy();
    @Test
    public void ensureNamingStrategyWorks() {
        final String string = "ef10195a-2405-4e93-b960-56f5e2c4c9b0";
        final String expected = "cef10195a24054e93b96056f5e2c4c9b0";
        assertThat(strategy.getName(string), is(expected));

    }

}