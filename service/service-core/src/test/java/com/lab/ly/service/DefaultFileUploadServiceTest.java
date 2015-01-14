package com.lab.ly.service;

import com.lab.ly.PersistenceTestCase;
import com.lab.ly.model.io.UploadedFile;
import org.junit.Test;

public class DefaultFileUploadServiceTest
        extends ServiceTestCase {

    @Test
    public void ensureSavingAValidFileThrowsNoException() {
        UploadedFile file = createFrom("hello,world");
        fileUploadService.upload(file);

    }

    private UploadedFile createFrom(String s) {
        final UploadedFile file = new UploadedFile();
        file.setData(s.getBytes());
        return file;
    }


}