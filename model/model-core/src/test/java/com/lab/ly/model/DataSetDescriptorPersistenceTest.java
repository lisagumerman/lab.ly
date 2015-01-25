package com.lab.ly.model;

import com.lab.ly.PersistenceTestCase;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.persistence.Persistence;

/**
 * Created by haswell on 1/24/15.
 */
public class DataSetDescriptorPersistenceTest extends PersistenceTestCase {

    @Test
    @Rollback
    public void ensureAdding() {
    }
}
