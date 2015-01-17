package com.lab.ly.service;

import com.lab.ly.FileUploadService;
import com.lab.ly.io.parsers.DelimitedFileParser;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.util.DataSets;
import com.lab.ly.model.InvalidDataSetException;
import com.lab.ly.model.io.UploadedFile;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.lab.ly.model.util.DataSets.from;

/**
 * Created by haswell on 1/13/15.
 */
@Service
public class DefaultFileUploadService implements FileUploadService {

    @Override
    public DataSet upload(UploadedFile file) {
        if(!(file == null || file.getData() == null)) {
            return from(file);
        }
        throw new InvalidDataSetException("");
    }

    @Override
    public String sayHello(String text) {
        return "Hello " + text;
    }

    @Override
    public DataSet get() {

        try {
            final InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("/data/test.csv");
            Reader reader = new InputStreamReader(inputStream);
            List<DelimitedFileParser.Row> rows = new DelimitedFileParser(",").parse(reader);
            final DataSet dataSet = new DataSet();
            DelimitedFileParser.Row headers = rows.get(0);
            dataSet.setHeaders(headers);
            for(int i = 1; i < rows.size(); ++i) {
                dataSet.addRow(rows.get(i));
            }
            return dataSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
