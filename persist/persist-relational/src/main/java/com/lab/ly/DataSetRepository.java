package com.lab.ly;

import com.lab.ly.model.ColumnDefinition;
import com.lab.ly.model.DataSet;
import com.lab.ly.model.DataSetDescriptor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by haswell on 1/24/15.
 */
public interface DataSetRepository {

    List<DataSetDescriptor> list();

    DataSetDescriptor get(Long id);

    DataSetDescriptor get(String key);

    Optional<DataSetDescriptor> find(String name);

    Set<ColumnDefinition> getColumns(Long id);

    Set<ColumnDefinition> getColumns(String key);

    DataSetDescriptor save(DataSet dataSet);

}
