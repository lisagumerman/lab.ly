package com.lab.ly.model.util;


import com.lab.ly.model.DataSet;
import com.lab.ly.model.io.UploadedFile;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DataSetsTest {


    static UploadedFile uploadedClasspathFile(String path) {
        try {
            final byte[] bytes = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(path).toURI()));
            final UploadedFile file = new UploadedFile();
            file.setData(bytes);
            return file;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    @Test
    public void ensureReadingValidCSVWorks() {
        DataSet set = DataSets.from(uploadedClasspathFile("data/test.csv"));
        assertNotNull(set);
        assertTrue(set.getHeaders().size() > 0);
    }


}