package com.lab.ly.persist;


import com.lab.ly.DataSetRepository;
import com.lab.ly.model.Column;
import com.lab.ly.model.DataSet;
import com.netflix.astyanax.Keyspace;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.io.Serializable;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

/**
 * Created by haswell on 1/17/15.
 */
public class CassandraDataSetRepositoryTest extends CassandraTestCase {

    @Inject
    private Keyspace keyspace;

    private DataSetRepository repository;

    @Test
    public void ensureRepositoryIsInjected() {
        assertNotNull(keyspace);
    }

    @Test
    public void ensureSavingRepositoryResultsInRepositoryBeingSaved() {
        final DataSet dataSet = sampleDataSet();
        UUID result = repository.save(dataSet);
        assertNotNull(result);
    }

    @Before
    public void setUp() throws Exception {

    }


    DataSet sampleDataSet() {
        final DataSet dataSet = new DataSet();
        dataSet.setColumn(createColumn("test", 1,2,3));
        return dataSet;
    }

    private Column<Serializable> createColumn(String test, Serializable...items) {
        return new Column<>(0, test, Arrays.asList(items));
    }
}
