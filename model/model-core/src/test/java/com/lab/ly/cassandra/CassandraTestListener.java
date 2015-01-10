package com.lab.ly.cassandra;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.io.File;

/**
 * Created by haswell on 1/7/15.
 */

@Configuration
public class CassandraTestListener implements TestExecutionListener {



    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        System.out.println("Done");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {

    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {

    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {

    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {

    }
}
