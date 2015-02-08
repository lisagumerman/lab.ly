package com.lab.ly.persist;


import com.lab.ly.DataSetRepository;
import com.lab.ly.model.Account;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.DataSetDescriptor;
import com.lab.ly.persist.csv.DelimitedFileReader;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.model.CqlResult;
import com.netflix.astyanax.model.Row;
import com.netflix.astyanax.serializers.StringSerializer;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.InputStreamReader;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by haswell on 1/17/15.
 */
@Transactional
public class CassandraDataSetRepositoryTest extends CassandraTestCase {

    @Inject
    private Keyspace keyspace;

    @Inject
    private DataSetRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Rollback
    public void ensureSavingAccountWithDataSetProducesExpectedResults() {
        DataSet dataSet = new DelimitedFileReader(',').read(new StringReader("hello,world\n1,2\n3,4"));
        Account account = new Account("test-account");
        DataSetDescriptor descriptor = repository.save(
                account,
                dataSet, "test_dataset1");
        assertThat(descriptor.getName(), is("test_dataset1"));
    }

    @Test
    @Rollback
    public void ensureSavingToExistingAccountWorks() {
        DataSet dataSet = new DelimitedFileReader(',').read(new StringReader("hello,world\n1,2\n3,4"));
        final Account account = new Account("test");
        entityManager.persist(account);
        entityManager.flush();
        DataSetDescriptor descriptor = repository.save(
                account,
                dataSet, "test_dataset2");
        assertThat(descriptor.getName(), is("test_dataset2"));
    }


    @Test
    public void ensureSavingLargeDataSetWorks() {
        DataSet dataSet = new DelimitedFileReader(',').read(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("datasets/csv/big.csv")));
        long t1 = System.currentTimeMillis();
        final Account account = new Account("test");
        entityManager.persist(account);
        entityManager.flush();
        DataSetDescriptor descriptor = repository.save(
                account,
                dataSet, "test_dataset4");
        assertThat(descriptor.getName(), is("test_dataset4"));
        long t2 = System.currentTimeMillis();
        System.out.println("Saved 5554 rows in " + (t2 - t1) + " milliseconds");
    }


    @Test
    public void ensureSavingDataSetAndRetrievingByColumnWorks() {

    }


    @Test
    @Rollback
    public void ensureSavingToAccountResultsInDataSetBeingAssociatedWithAccount() {

        DataSet dataSet = new DelimitedFileReader(',').read(new StringReader("hello,world\n1,2\n3,4"));
        final Account account = new Account("test2");
        entityManager.persist(account);
        entityManager.flush();
        DataSetDescriptor descriptor = repository.save(
                account,
                dataSet, "test_dataset3");
        assertThat(descriptor.getName(), is("test_dataset3"));
        assertThat(repository.list(account).size(), is(1));
    }
    
    @Test
    public void ensureListingDescriptorsOnAccountProducesExpectedResults() {
        
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
