package com.lab.ly.model;

import java.util.List;

public interface DataSource {

    String getName();

    List<String> getDataSetNames();

    DataSet getDataSet(String name);

    Boolean hasDataSet(String name);

    boolean addDataSet(String name, DataSet dataSource);

    DataSet removeDataSet(String name);

}
