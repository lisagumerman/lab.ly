package com.lab.ly;

import com.lab.ly.model.DataSet;
import com.lab.ly.model.DataSetDescriptor;
import com.lab.ly.model.EntityCoordinate;
import com.lab.ly.model.io.UploadedFile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by haswell on 1/17/15.
 */
@Repository
public interface DataSetRepository {

    public DataSetDescriptor save(DataSet dataSet);

    public DataSetDescriptor save(UploadedFile file);


}
