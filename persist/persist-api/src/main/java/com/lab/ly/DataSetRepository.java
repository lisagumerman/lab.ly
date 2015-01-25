package com.lab.ly;

import com.lab.ly.model.DataSet;
import com.lab.ly.model.DataSetDescriptor;
import com.lab.ly.model.EntityCoordinate;

import java.util.List;
import java.util.UUID;

/**
 * Created by haswell on 1/17/15.
 */
public interface DataSetRepository {

    public DataSetDescriptor save(DataSet dataSet);


}
