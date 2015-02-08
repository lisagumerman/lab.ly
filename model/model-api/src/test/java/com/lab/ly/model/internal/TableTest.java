package com.lab.ly.model.internal;

import com.lab.ly.model.SerializationTestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TableTest extends SerializationTestCase {

    public TableTest() {
        super(Format.Json, Table.class, ArrayList.class, MappedList.class, Integer.class);
    }

    @Test
    public void ensureListOfListIsSerializedCorrectly() {
    }

    @Test
    public void ensureTableIsSerializedCorrectly() {

    }

}