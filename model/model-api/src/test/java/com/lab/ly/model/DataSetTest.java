package com.lab.ly.model;


import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DataSetTest extends SerializationTestCase {

    public DataSetTest() {
        super(Format.Json, DataSet.class);
    }

    @Test
    public void ensureAddingColumnAddsHeader() {
        DataSet ds = new DataSet();
        Column<Integer> column =
                new Column<>(0, "test", 1,2,3,4);
        ds.setColumn(column);
        assertTrue(ds.getHeaders().contains("test"));
    }

    @Test
    public void ensureAddingARowWorks() {
        DataSet ds = new DataSet();
        Column<String> column = new Column<>(0, "test", "1", "2", "3", "4");
        ds.setColumn(column);
        DataSet copy = copy(ds);
        assertThat(copy.getColumn("test"), is(column));
    }

    @Test
    public void ensureAddingAHeaderWorks() {
        DataSet ds = new DataSet();
        Column<Integer> column =
                new Column<>(0, "test", 1,2,3,4);
        ds.setColumn(column);
        DataSet copy = copy(ds);
    }

    @Test
    public void ensureensureAddingAcolumnContainingIntegersWorks() {
        DataSet ds = new DataSet();
        Column<Integer> column =
                new Column<>(0, "test", 1,2,3,4);
        ds.setColumn(column);
        DataSet copy = copy(ds);
    }

    @Test
    public void ensureAddingAColumnContainingHTmlworks() {
        DataSet ds = new DataSet();
        ds.setColumn("test", Arrays.asList("Hello <i> world</i>",
                "<h1>How are</h1><i>You?</i>"));
        DataSet copy = copy(ds, Format.Json);
        assertThat(copy.getColumn("test"), is(ds.getColumn("test")));
    }

}