package com.lab.ly.model;

import java.util.List;
import java.util.Set;

/**
 * Created by haswell on 1/24/15.
 */
public interface MigrationManager {

    Boolean hasRun();

    void registerTask(MigrationTask task);

    Set<MigrationTask> getMigrationTasks();

    MigrationTask getMigrationTask(String name);

    void apply(PersistenceContext context);



}
