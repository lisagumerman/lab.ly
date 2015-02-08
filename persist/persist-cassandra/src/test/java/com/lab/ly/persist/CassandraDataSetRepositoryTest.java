package com.lab.ly.persist;


import com.lab.ly.DataSetRepository;
import com.lab.ly.model.Column;
import com.lab.ly.model.DataSet;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.model.CqlResult;
import com.netflix.astyanax.model.Row;
import com.netflix.astyanax.serializers.StringSerializer;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @Test
    public void ensureSavingAccountWithDataSetProducesExpectedResults() {



    }

    @Test
    public void ensureCanCreateKeyspace() throws ConnectionException {



        ColumnFamily<String, String> CF_USER_INFO =
                new ColumnFamily<>(
                        "Standard1",              // Column Family Name
                        StringSerializer.get(),   // Key Serializer
                        StringSerializer.get());

        keyspace.createColumnFamily(CF_USER_INFO, null);

        MutationBatch m = keyspace.prepareMutationBatch();

        m.withRow(CF_USER_INFO, "acct1234")
                .putColumn("firstname", "john", null)
                .putColumn("lastname", "smith", null)
                .putColumn("address", "555 Elm St", null)
                .putColumn("age", 30, null);

        m.withRow(CF_USER_INFO, "acct1235")
                .putColumn("firstname", "josiah", null)
                .putColumn("lastname", "smith", null)
                .putColumn("address", "555 Elm St", null)
                .putColumn("age", 30, null)
                .putColumn("thing", 22, null);


        try {
            OperationResult<Void> result = m.execute();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }

        try {
            OperationResult<CqlResult<String, String>> result
                    = keyspace.prepareQuery(CF_USER_INFO)
                    .withCql("SELECT * FROM Standard1;")
                    .execute();
            for (Row<String, String> row : result.getResult().getRows()) {
                System.out.println(
                        row.getColumns().getColumnByName("firstname")
                                .getStringValue()

                );
            }
        } catch (ConnectionException e) {

        }
    }


}
