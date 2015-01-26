package com.lab.ly.persist.cassandra;

import com.lab.ly.DataSetRepository;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.DataSetDescriptor;
import com.netflix.astyanax.Keyspace;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by haswell on 1/17/15.
 */
@Repository
@Named("cassandra-repository")
public class CassandraDataSetRepository implements DataSetRepository {

    @Inject
    private Keyspace keyspace;

    @Override
    public DataSetDescriptor save(DataSet dataSet) {
        return null;
    }
}
