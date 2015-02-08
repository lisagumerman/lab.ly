package com.lab.ly.persist.csv;

import com.lab.ly.model.DataSet;
import com.lab.ly.model.InvalidDataSetException;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class DelimitedFileReaderTest {


    @Test(expected = InvalidDataSetException.class)
    public void ensureReadingNullProducesCorrectResult() {
        new DelimitedFileReader(',').read(null);
    }
    
    @Test
    public void ensureReadingEmptyProducesEmptyDataSet() {
        DataSet read = read("");
        assertThat(read.getColumnCount(), is(0));
    }

    @Test
    public void ensureReadingColumnWithSingleDelimiterProducesExpectedResults() {
        DataSet read = read(",");
        assertThat(read.getColumnCount(), is(0));
    }

    @Test
    public void ensureReadingColumnWithSingleHeaderAndNoValuesWithUnterminatedLineWorks() {
        DataSet read = read("hello,");
        assertThat(read.getColumnCount(), is(1));
        assertThat(read.getColumn("hello").size(), is(0));

    }

    @Test
    public void ensureReadingSingleColumnWithTrailingDelimiterWorks() {
        DataSet read = read("hello,\nworld");
        assertThat(read.getColumn("hello").size(), is(1));
    }

    @Test
    public void ensureReadingLargeCsvWorks() {
        InputStream is = ClassLoader.getSystemResourceAsStream("datasets/csv/simple.csv");
        DataSet dataSet = new DelimitedFileReader(',').read(new InputStreamReader(is));
        assertThat(dataSet.getColumnCount(), is(48));
        for(String header : dataSet.getHeaders()) {
            assertThat(header, is(not(nullValue())));
        }

    }

    DataSet read(String input) {
        return new DelimitedFileReader(',')
                .read(new StringReader(input));

    }

}