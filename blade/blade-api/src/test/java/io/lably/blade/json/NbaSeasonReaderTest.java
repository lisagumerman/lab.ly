package io.lably.blade.json;

import com.lab.ly.model.DataSet;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class NbaSeasonReaderTest {

    @Test
    public void ensureCanReadFromNba() throws Throwable {
        List<DataSet> ds = new NbaSeasonReader().
                readDataSet(ClassLoader.getSystemResourceAsStream("sites/nba/json/2013.json"));
        for(DataSet d : ds) {
            for(String header : d.getHeaders()) {
                System.out.println(d.getColumn(header));
            }
        }
    }

}