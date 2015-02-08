package com.lab.ly.model.internal;

import com.lab.ly.model.MigrationManager;
import com.lab.ly.model.MigrationTask;
import com.lab.ly.model.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by haswell on 2/7/15.
 */
public class DefaultMigrationManager implements MigrationManager {

    static final Logger logger = LoggerFactory
            .getLogger(DefaultMigrationManager.class);

    final Map<String, MigrationTask> tasks;

    final AtomicBoolean hasRun = new AtomicBoolean();


    public DefaultMigrationManager() {
        this.tasks = new LinkedHashMap<>();
    }


    @Override
    public void registerTask(MigrationTask task) {
        if (this.tasks.put(task.getName(), task) != null) {
            throw new IllegalArgumentException("Error:  task named " +
                    task.getName() + " already exists!");
        }
    }

    @Override
    public Set<MigrationTask> getMigrationTasks() {
        return new LinkedHashSet<>(tasks.values());
    }

    @Override
    public MigrationTask getMigrationTask(String name) {
        final MigrationTask task = tasks.get(name);
        if (task != null) {
            return task;
        }
        throw new NoSuchElementException("Task named '" + name +
                "' was not found in this migration manager!");
    }

    public Boolean hasRun() {
        return hasRun.get();
    }

    @Override
    public void apply(PersistenceContext context) {
        tasks.values().forEach(task -> {
            logger.info("performing migration task: '{}' ", task.getName());
            task.apply(context);
            logger.info("Successfully performed migration task");
        });
        hasRun.set(true);
    }
}
