package com.lab.ly.service;

import com.lab.ly.PersistenceTestCase;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.io.UploadedFile;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultFileUploadServiceTest
        extends ServiceTestCase {

    @Test
    public void ensureSavingAValidFileThrowsNoException() {
        final UploadedFile file = createFrom("hello,world");
        final DataSet dataSet = fileUploadService.upload(file);
        assertThat(dataSet.getHeaders(),
                is(new HashSet<>(Arrays.asList("hello", "world"))));
    }

    private UploadedFile createFrom(String s) {
        final UploadedFile file = new UploadedFile();
        file.setData(s.getBytes());
        return file;
    }


}