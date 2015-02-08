package com.lab.ly.persist.cassandra;

import com.lab.ly.DataSetRepository;
import com.lab.ly.model.*;
import com.lab.ly.model.Column;
import com.netflix.astyanax.ColumnListMutation;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.serializers.StringSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by haswell on 1/17/15.
 */
@Repository
@Transactional
@Named("cassandra-repository")
public class CassandraDataSetRepository implements DataSetRepository {

    @Inject
    private Keyspace keyspace;

    @javax.persistence.PersistenceContext
    private EntityManager entityManager;

    @Override
    public DataSetDescriptor save(
            Account account,
            DataSet dataSet,
            String name) {
        try {
            final DataSetDescriptor descriptor =
                    extractDescriptor(dataSet, name, account);
            account.addDataSet(descriptor);
            entityManager.merge(account);
            return descriptor;
        } catch(Exception ex) {
            throw new InvalidDataSetException(ex);
        }
    }


    @Override
    public Set<DataSetDescriptor> list(Account account) {
        return new LinkedHashSet<>(entityManager.createQuery(
                "select descriptor from " +
                        "DataSetDescriptor descriptor " +
                        "where descriptor.owner.id = :id",
                DataSetDescriptor.class).setParameter("id", account.getId())
                .getResultList());
    }

    @Override
    public <T extends Serializable> Column<T> getColumn(Account account, String name) {
        return null;
    }


    private DataSetDescriptor extractDescriptor(DataSet dataSet, String name, Account account) throws ConnectionException {
        DataSetDescriptor result = new DataSetDescriptor();
        result.setName(name);
        int index = 0;
        addDefinitions(dataSet, result, index);
        saveDataSetToCassandra(dataSet, result, account);

        return result;
    }

    private void saveDataSetToCassandra(
            DataSet dataSet,
            DataSetDescriptor result,
            Account account) throws ConnectionException {
        ColumnFamily<String, String> columnFamily =
                new ColumnFamily<>(
                        result.getName(),
                        StringSerializer.get(),
                        StringSerializer.get()
                );
        keyspace.createColumnFamily(columnFamily, null);

        saveData(columnFamily, dataSet, result);
    }

    private void saveData(
            ColumnFamily<String, String> columnFamily,
            DataSet dataSet,
            DataSetDescriptor result) throws ConnectionException {
        final MutationBatch batch = keyspace.prepareMutationBatch();
        final String datasetName = result.getKey();
        ColumnListMutation<String> stringColumnListMutation =
                batch.withRow(columnFamily, datasetName);
        for(ColumnDefinition columnDefinition : result.getColumnDefinitions()) {
            saveColumn(dataSet, stringColumnListMutation, columnDefinition);
        }
        batch.execute();
    }

    private void saveColumn(DataSet dataSet, ColumnListMutation<String> stringColumnListMutation, ColumnDefinition columnDefinition) {
        final String columnName = String.valueOf(columnDefinition.getIndex());
        final Column<String> column = dataSet.getColumn(columnDefinition.getName());
        for(String rowValue : column) {
            stringColumnListMutation.putColumn(columnName, rowValue, null);
        }
    }

    private void addDefinitions(DataSet dataSet, DataSetDescriptor result, int index) {
        for(String header : dataSet.getHeaders()) {
            final ColumnDefinition definition = new ColumnDefinition(
                    ColumnType.String,
                    header,
                    index++
            );
            result.setKey("c" + UUID.randomUUID().toString().replaceAll("-", "_"));
            result.addColumn(definition);
        }
    }
}
