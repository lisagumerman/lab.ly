package com.lab.ly.service;

import com.lab.ly.FileUploadService;
import com.lab.ly.PersistenceTestCase;
import com.lab.ly.configuration.services.ServiceTestConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by haswell on 1/13/15.
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceTestConfiguration.class
})
public class ServiceTestCase extends PersistenceTestCase {

    @Inject
    protected FileUploadService fileUploadService;

    @Test
    public void ensureServicesAreInjectedCorrectly() {
        assertThat(fileUploadService, is(not(nullValue())));
    }
}
