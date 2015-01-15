package com.lab.ly.io.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haswell on 1/14/15.
 *
 * TODO: make this class not suck and test it
 */
public class DelimitedFileParser {


    public List<Row> parse(Reader reader) throws IOException {
        final List<Row> results = new ArrayList<>();
        final BufferedReader r = new BufferedReader(reader);
        readUnconditionally(results, r);
        return results;
    }

    private void readUnconditionally(List<Row> results, BufferedReader r) throws IOException {
        String line;
        while((line = r.readLine()) != null) {
            Row row = new Row();
            row.addAll(Arrays.asList(line.split(separator)));
            results.add(row);
        }
    }

    public static class Row extends ArrayList<String> {  }


    final String separator;
    public DelimitedFileParser(String separator) {
        this.separator = separator;
    }


    public List<Row> parse(String string) {
        String[] results = string.split("\n");
        return Arrays.stream(results).map(it -> {
            final Row row = new Row();
            row.addAll(Arrays.asList(it.split(separator)));
            return row;
        }).collect(Collectors.toList());
    }

}
