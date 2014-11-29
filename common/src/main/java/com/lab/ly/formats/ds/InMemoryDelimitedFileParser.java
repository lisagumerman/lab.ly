package com.lab.ly.formats.ds;

import com.lab.ly.common.DataSet;
import com.lab.ly.common.MemoryMappedSerializableDataSet;
import com.lab.ly.common.MemoryMappedSerializableTable;

import java.io.InputStream;
import java.util.*;

/**
 * User: haswell
 * Date: 11/28/14
 * Time: 4:30 PM
 */
public class InMemoryDelimitedFileParser implements Parser<DataSet, String> {
    @Override
    public DataSet parse(String input) {
        List<String> headers;

        final String[] lines = input.split("\\n");
        headers = extractFirst(lines);
        Map<String, List<String>> contents;
        if(lines.length > 0) {
            contents = extractAll(lines, headers);
        } else {
            contents = new HashMap<>();
        }
        final MemoryMappedSerializableDataSet dataSet = new MemoryMappedSerializableDataSet();
        dataSet.setColumns(headers);
        dataSet.setTable(new MemoryMappedSerializableTable());
        return dataSet;
    }


    final List<String> extractFirst(String[] fst) {
        String[] headerLine = fst[0].split(",");
        return Arrays.asList(headerLine);
    }

    final Map<String, List<String>> extractAll(String[] lines, List<String> headers) {
        final Map<String, List<String>> contents = new HashMap<>();
        for(int i = 1; i < lines.length; ++i) {
            final String[] line = lines[i].split(",");
            for(int j = 0; j < line.length; ++j) {
                final String cell = line[j];
                List<String> column = contents.get(headers.get(j));
                if(column == null) {
                    column = new ArrayList<>();
                    contents.put(headers.get(j), column);
                }
                column.add(cell);
            }
        }
        return contents;
    }
}
