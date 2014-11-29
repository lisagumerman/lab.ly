package com.lab.ly.model;


import org.junit.Test;

import java.util.Arrays;

public class MemoryMappedDataSetTest extends SerializationTestCase {

    public MemoryMappedDataSetTest() {
        super(HeaderSet.class, MemoryMappedDataSet.class, Table.class);
    }

    @Test
    public void ensureDataSetIsSerializedCorrectly() {
        final MemoryMappedDataSet set = new MemoryMappedDataSet();
        set.addColumn("test", Arrays
            .asList("one", "two", "three"));
        System.out.println(serialize(set));
    }

}