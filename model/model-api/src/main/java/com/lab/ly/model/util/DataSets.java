package com.lab.ly.model.util;
import com.lab.ly.io.parsers.DelimitedFileParser;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.InvalidDataSetException;
import com.lab.ly.model.io.UploadedFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static com.lab.ly.Preconditions.*;
/**
 * Created by haswell on 1/13/15.
 */
public class DataSets {
    private DataSets() {
        throw new UnsupportedOperationException("No datasets for you!");
    }


    public static DataSet from(UploadedFile file) {
        final DelimitedFileParser parser = new DelimitedFileParser(",");
        try (final Reader reader = new InputStreamReader(
                new ByteArrayInputStream(file.getData()))) {
            final List<DelimitedFileParser.Row> rows = parser.parse(reader);
            final DataSet dataSet = new DataSet();
            dataSet.setHeaders(rows.get(0));
            for(int i = 1; i < rows.size(); ++i) {
                dataSet.addRow(rows.get(i));
            }
            return dataSet;
        } catch(IOException ex) {
            throw new InvalidDataSetException(ex);
        }
    }
}
