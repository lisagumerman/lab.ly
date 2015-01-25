package com.lab.ly.model;

import javax.sql.DataSource;

/**
 * Created by haswell on 1/24/15.
 */
public class PersistenceContext {
    private final DataSource dataSource;

    public PersistenceContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
