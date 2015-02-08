package com.lab.ly.model;

/**
 * Created by haswell on 2/7/15.
 */
public abstract class AbstractMigrationTask implements MigrationTask {
    final String name;

    protected AbstractMigrationTask(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
