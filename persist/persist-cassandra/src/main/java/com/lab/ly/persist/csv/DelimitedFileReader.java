package com.lab.ly.persist.csv;

import com.lab.ly.model.Column;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.InvalidDataSetException;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.Iterator;
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
        validate(reader);
        try {
            return read(createParser(reader, delimiter));
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private DataSet read(ICsvListReader reader) throws IOException {
        List<String> headers = reader.read();
        final DataSet result = new DataSet();
        if(headers != null) {
            unpackHeaders(result, headers);
            unpackContents(result, headers, reader);
        }
        return result;
    }

    private void unpackContents(
            DataSet result,
            List<String> headers,
            ICsvListReader reader) throws IOException {
        final List<String> currentLine = reader.read();
        if(!(currentLine == null || currentLine.isEmpty())) {
            unpackLine(result, headers, currentLine);
        }
    }

    private void unpackLine(DataSet result, List<String> headers, List<String> currentLine) {
        final Iterator<String> lineIterator = currentLine.iterator();
        final Iterator<String> headerIterator = headers.iterator();
        while(headerIterator.hasNext()) {
            final String header = headerIterator.next();
            if(lineIterator.hasNext()) {
                Column<Serializable> column = result.getColumn(header);
                column.add(lineIterator.next());
            }
        }
    }

    private void unpackHeaders(DataSet result, List<String> headers) {
        for(String header : headers) {
            if(header != null) {
                final String normalized = header.trim();
                if(!normalized.isEmpty()) {
                    result.addColumn(normalized, new Column<>());
                }
            }
        }

    }

    private ICsvListReader createParser(Reader reader, char delimiter) {
        return new CsvListReader(reader, CsvPreference.STANDARD_PREFERENCE);
    }

    private void validate(Reader input) {
        if(input == null) {
            throw new InvalidDataSetException("Data set cannot be null");
        }
    }

}
