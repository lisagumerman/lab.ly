package com.lab.ly.persist;

import com.google.common.collect.ImmutableMap;
import com.lab.ly.configurations.model.ModelTestConfiguration;
import com.lab.ly.persist.cassandra.CassandraDataSetRepository;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.CountingConnectionPoolMonitor;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by haswell on 1/17/15.
 *
 * TODO:
 *
 * Connect to embedded cassandra
 */
@Configuration
@ComponentScan(
        basePackages = "com.lab.ly.persist.cassandra"
)
@Import(ModelTestConfiguration.class)
public class CassandraTestConfiguration {

    static final Logger logger = Logger.getLogger(CassandraTestCase.class.getName());
    static {

        logger.log(Level.INFO, "Starting cassandra...");

        try {
            EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
        logger.log(Level.INFO, "cassandra started...");
    }




    @Bean
    public Keyspace keyspace() throws ConnectionException {

        AstyanaxContext<Keyspace> context = new AstyanaxContext.Builder()
                .forCluster("ClusterName")
                .forKeyspace("TestKeyspace")
                .withAstyanaxConfiguration(new AstyanaxConfigurationImpl()
                                .setDiscoveryType(NodeDiscoveryType.RING_DESCRIBE)
                )
                .withConnectionPoolConfiguration(new ConnectionPoolConfigurationImpl("MyConnectionPool")
                                .setPort(9171)
                                .setMaxConnsPerHost(1)
                                .setSeeds("127.0.0.1:9171")
                )
                .withConnectionPoolMonitor(new CountingConnectionPoolMonitor())
                .buildKeyspace(ThriftFamilyFactory.getInstance());

        context.start();
        Keyspace keyspace = context.getClient();

        keyspace.createKeyspace(ImmutableMap.<String, Object>builder()
                        .put("strategy_options", ImmutableMap.<String, Object>builder()
                                .put("replication_factor", "1")
                                .build())
                        .put("strategy_class",     "SimpleStrategy")
                        .build()
        );

        return keyspace;
    }


}
