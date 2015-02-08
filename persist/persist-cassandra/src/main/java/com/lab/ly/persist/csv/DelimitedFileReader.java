package com.lab.ly.persist.csv;

import com.lab.ly.model.DataSet;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by haswell on 2/8/15.
 */
public class DelimitedFileReader {

    final char delimiter;

    public DelimitedFileReader(final char delimiter) {
        this.delimiter = delimiter;
    }


    public DataSet read(Reader reader) {
        try {
            return read(createParser(reader, delimiter));
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private DataSet read(ICsvListReader reader) throws IOException {
        List<String> header = reader.read();
        return null;
    }

    private ICsvListReader createParser(Reader reader, char delimiter) {
        return new CsvListReader(reader, CsvPreference.STANDARD_PREFERENCE);
    }


}
