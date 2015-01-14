package com.lab.ly.service;

import com.lab.ly.FileUploadService;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.DataSets;
import com.lab.ly.model.InvalidDataSetException;
import com.lab.ly.model.io.UploadedFile;
import org.springframework.stereotype.Service;

/**
 * Created by haswell on 1/13/15.
 */
@Service
public class DefaultFileUploadService implements FileUploadService {

    @Override
    public DataSet upload(UploadedFile file) {
        if(!(file == null || file.getData() == null)) {
            return DataSets.from(file);
        }
        throw new InvalidDataSetException("");
    }
}
