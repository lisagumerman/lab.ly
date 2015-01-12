package com.lab.ly;

import com.google.common.collect.ImmutableMap;
import com.lab.ly.cassandra.CassandraTestListener;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.CountingConnectionPoolMonitor;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.serializers.StringSerializer;
import com.netflix.astyanax.test.EmbeddedCassandra;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.IdentityHashMap;



/**
 * Created by haswell on 1/6/15.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CassandraTestConfiguration.class)
@TestExecutionListeners(
    mergeMode =
        TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
    listeners = {
            CassandraTestListener.class
    }
)
public class CassandraPersistenceTestCase {

    @Test
    public void ensureCassandraStartingWorks() throws Exception {
        final EmbeddedCassandra cassandra = new EmbeddedCassandra();
        cassandra.start();


        AstyanaxContext<Keyspace> context = new AstyanaxContext.Builder()
                .forCluster("ClusterName")
                .forKeyspace("KeyspaceName")
                .withAstyanaxConfiguration(new AstyanaxConfigurationImpl()
                                .setDiscoveryType(NodeDiscoveryType.RING_DESCRIBE)
                )
                .withConnectionPoolConfiguration(new ConnectionPoolConfigurationImpl("MyConnectionPool")
                                .setPort(9160)
                                .setMaxConnsPerHost(1)
                                .setSeeds("127.0.0.1:9160")
                )
                .withConnectionPoolMonitor(new CountingConnectionPoolMonitor())
                .buildKeyspace(ThriftFamilyFactory.getInstance());
        Keyspace keyspace = context.getClient();

        context.start();
        keyspace.createKeyspace(ImmutableMap.<String, Object>builder()
                        .put("strategy_options", ImmutableMap.<String, Object>builder()
                                .put("replication_factor", "1")
                                .build())
                        .put("strategy_class", "SimpleStrategy")
                        .build()
        );

        final ColumnFamily<String, String> family =
                new ColumnFamily<>(
                        "test", new StringSerializer(), new StringSerializer());


        keyspace.createColumnFamily(family, new IdentityHashMap<>());
        long t1 = System.currentTimeMillis();

        MutationBatch batch = keyspace.prepareMutationBatch();
        batch.execute();

        MutationBatch m = keyspace.prepareMutationBatch();

        long rowKey = 1234;

// Setting columns in a standard column

        for(int i = 0; i < 100000; ++i) {

            m.withRow(family, "1234" + i)
                    .putColumn("Column1" + i, "X", null)
                    .putColumn("Column2" + i, "X", null);

            m.withRow(family, "12345" + i)
                    .putColumn("Column1", "Y", null);
            if(i % 1000 == 0) {
                OperationResult<Void> result = m.execute();
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Put 1000 columns in "  + (t1 - t2) / 1000);
    }



}
