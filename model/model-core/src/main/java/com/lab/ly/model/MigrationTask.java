package com.lab.ly.model;

/**
 * Created by haswell on 1/24/15.
 */
public interface MigrationTask {

    String getName();

    void apply(PersistenceContext context);


}
