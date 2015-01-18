package com.lab.ly.persist.cassandra;

import com.lab.ly.DataSetRepository;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.EntityCoordinate;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.query.PreparedCqlQuery;
import com.netflix.astyanax.serializers.IntegerSerializer;
import com.netflix.astyanax.serializers.StringSerializer;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

/**
 * Created by haswell on 1/17/15.
 */
@Repository
@Named("cassandra-repository")
public class CassandraDataSetRepository implements DataSetRepository {

    @Inject
    private Keyspace keyspace;

    static final boolean columnFamilyCreated = false;

    static ColumnFamily<Integer, String> family;



    @Override
    public UUID save(DataSet dataSource) {
        createColumnFamily();
        final UUID uuid = UUID.randomUUID();
        final String tableName = "c" +
                uuid.toString().replaceAll("-", "");

        keyspace.prepareQuery(family)
                .withCql("create table " + tableName + " (test int)");

        final String prepared = "insert into " + tableName + " (test) values (?)";

        PreparedCqlQuery<Integer, String> integerStringPreparedCqlQuery = keyspace.
                prepareQuery(family)
                .withCql(prepared).asPreparedStatement();
        for(Object o : dataSource.getColumn("test")) {
            integerStringPreparedCqlQuery.withIntegerValue((Integer) o);
            try {
                integerStringPreparedCqlQuery.execute();
            } catch (ConnectionException e) {
                throw new RuntimeException(e);
            }
            break;

        }

        return uuid;
    }

    @Override
    public DataSet get(UUID id) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }

    @Override
    public List<EntityCoordinate<UUID, String>> listCoordinates() {
        return null;
    }

    void createColumnFamily() {
        if(!columnFamilyCreated) {
            family = ColumnFamily.newColumnFamily(
                    "Cql3CF",
                    IntegerSerializer.get(),
                    StringSerializer.get());
        }
    }
}
