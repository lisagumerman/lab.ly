package com.lab.ly;

import com.lab.ly.configurations.model.ModelTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by haswell on 1/5/15.
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ModelTestConfiguration.class)
public class PersistenceTestCase {


    @PersistenceContext
    protected EntityManager entityManager;

    @Test
    public void ensureEntityManagerIsInjected() {
        assertThat(entityManager, is(not(nullValue())));
    }
}
