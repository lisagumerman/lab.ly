package com.lab.ly.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by haswell on 1/24/15.
 */
public class DataSetSerializationTest extends SerializationTestCase {


    public DataSetSerializationTest() {
        super(Format.Json,
                DataSet.class,
                DataSetDescriptor.class,
                Account.class,
                ColumnDefinition.class
        );
    }
    
    @Test
    public void ensureDataSetDescriptorWithDataSetCanBeSerialized() {
        DataSetDescriptor descriptor = new DataSetDescriptor();
        descriptor.setName("joe's test dataset");
        Account account = new Account();
        account.setName("test");
        account.addDataSet(descriptor);
        descriptor.addColumn(new ColumnDefinition(ColumnType.Numeric, "test", 1));
        descriptor.addColumn(new ColumnDefinition(ColumnType.String, "other", 1));
        assertThat(copy(account).getDataSets().size(), is(1));
        assertThat(copy(account).getDataSets().iterator().next().getColumnDefinitions().size(), is(2));
    }


}
