package com.lab.ly.model;

import com.lab.ly.PersistenceTestCase;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.inject.Inject;
import javax.persistence.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by haswell on 1/24/15.
 */
public class DataSetDescriptorPersistenceTest extends PersistenceTestCase {
    @javax.persistence.PersistenceContext
    private EntityManager deletionEntityManager;

    @Test
    @Rollback
    public void ensureAddingADataSetToAccountWorks() {

        final Account account = new Account();
        account.setName("Test account");
        final DataSetDescriptor dataSetDescriptor = new DataSetDescriptor();
        dataSetDescriptor.setName("joe's data set");
        dataSetDescriptor.addColumn(new
                ColumnDefinition(ColumnType.Numeric, "Test", 0));
        account.addDataSet(dataSetDescriptor);
        entityManager.persist(account);

    }

    @Test
    @Rollback
    public void ensureAddingMultipleDataSetsToAccountWorks() {
        final Account account = new Account();
        account.setName("Test account");
        DataSetDescriptor dataSetDescriptor = new DataSetDescriptor();
        dataSetDescriptor.setName("joe's data set");
        dataSetDescriptor.addColumn(new
                ColumnDefinition(ColumnType.Numeric, "Test", 0));
        account.addDataSet(dataSetDescriptor);
        entityManager.persist(account);
        entityManager.flush();
        dataSetDescriptor = entityManager.find(DataSetDescriptor.class, dataSetDescriptor.getId());
        assertNotNull(dataSetDescriptor.getId());
        assertThat(dataSetDescriptor.getColumnDefinitions().size(), is(1));
    }

    @Test
    public void ensureMultipleDataSetsAreSaved() {

    }
}
