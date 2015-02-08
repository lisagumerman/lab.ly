package com.lab.ly.model;

import com.lab.ly.PersistenceTestCase;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

/**
 * Created by haswell on 2/7/15.
 */
public class MigrationManagerTest extends PersistenceTestCase {


    @Inject
    private MigrationManager migrationManager;


    @Test
    public void ensureMigrationManagerHasRun() {
        assertTrue(migrationManager.hasRun());
    }
}
