package com.lab.ly.persist.cassandra;

import com.lab.ly.DataSetRepository;
import com.lab.ly.model.*;
import com.lab.ly.model.io.UploadedFile;
import com.lab.ly.model.util.DataSets;
import com.lab.ly.persist.NamingStrategy;
import com.netflix.astyanax.ColumnListMutation;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.serializers.StringSerializer;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.awt.datatransfer.StringSelection;

/**
 * Created by haswell on 1/17/15.
 */
@Repository
@Named("cassandra-repository")
public class CassandraDataSetRepository implements DataSetRepository {

    @Inject
    private Keyspace keyspace;

    @Inject
    private NamingStrategy namingStrategy;

    @Override
    public DataSetDescriptor save(DataSet dataSet) {
        return null;
    }

    @Override
    public DataSetDescriptor save(UploadedFile file) {
        final DataSet dataSet = DataSets.from(file);
        final DataSetDescriptor descriptor = convertToDescriptor(dataSet);
        saveDataSet(dataSet, descriptor);
        return descriptor;
    }

    private void saveDataSet(DataSet dataSet, DataSetDescriptor descriptor) {
        final ColumnFamily<String, String> columnFamily = createColumnFamily(
                namingStrategy.getName(dataSet.getKey()));
        saveToColumnFamily(dataSet, descriptor, columnFamily);

    }

    private void saveToColumnFamily(
            DataSet dataSet,
            DataSetDescriptor descriptor,
            ColumnFamily<String, String> columnFamily) {
        final MutationBatch mutationBatch = keyspace.prepareMutationBatch();
        for(ColumnDefinition definition :  descriptor.getColumnDefinitions()) {
            final Column<String> column = dataSet.getColumn(definition.getName());
            final String columnName = String.valueOf(definition.getIndex());
            save(mutationBatch, column, columnName, columnFamily, namingStrategy.getName(dataSet.getKey()));
        }



    }

    private void save(
            MutationBatch mutationBatch,
            Column<String> column,
            String columnName,
            ColumnFamily<String, String> columnFamily,
            String rowKey) {
        final ColumnListMutation<String> mutation =
                mutationBatch.withRow(columnFamily, rowKey);
        for(String element : column) {

        }
    }

    private ColumnFamily<String, String> createColumnFamily(String name) {
        final ColumnFamily<String, String> cf = new ColumnFamily<>(name,
                StringSerializer.get(),
                StringSerializer.get());
        try {
            keyspace.createColumnFamily(cf, null);
            return cf;
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    private DataSetDescriptor convertToDescriptor(DataSet dataSet) {
        final DataSetDescriptor descriptor = new DataSetDescriptor();
        int count = 0;

        for(String header : dataSet.getHeaders()) {
            descriptor.addColumn(
                    new ColumnDefinition(ColumnType.String, header, count++));
        }
        dataSet.setKey(descriptor.getKey());
        return descriptor;
    }


}
