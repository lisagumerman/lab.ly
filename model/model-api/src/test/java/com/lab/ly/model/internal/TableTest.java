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
        final List<String> items = Arrays.asList("1", "2", "3");
        System.out.println(marshal(new MappedList<>(items)));
    }

    @Test
    public void ensureTableIsSerializedCorrectly() {
        final Table table = new Table();
        table.setDraw(1);
//        final MappedList values = new MappedList(
//                Arrays.asList("1", 1, new Object())
//        );
//        table.setData(values);
//        System.out.println(marshal(table));

    }

}