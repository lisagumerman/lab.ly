package com.lab.ly.persist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CassandraTestConfiguration.class)
public class CassandraTestCase {

    @Test
    public void ensureWhatever() {

    }
}
