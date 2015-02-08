package com.lab.ly.model.internal;

import com.lab.ly.model.AbstractMigrationTask;
import com.lab.ly.model.PersistenceContext;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by haswell on 2/7/15.
 */
public class DatabaseMigrationTask extends AbstractMigrationTask {

    static final Logger logger = LoggerFactory
            .getLogger(DatabaseMigrationTask.class);
    final String location;
    final String migrationTableName;

    public DatabaseMigrationTask(
            final String location,
            final String migrationSchemaName) {
        super("database migration");
        this.location = location;
        this.migrationTableName = migrationSchemaName;

    }

    @Override
    public void apply(PersistenceContext context) {
        logPreMigration();
        final Flyway flyway = new Flyway();
        flyway.setLocations(location);
        flyway.setTable(migrationTableName);
        flyway.setDataSource(context.getDataSource());
        flyway.migrate();
        logPostMigration();
    }

    private void logPreMigration() {
        logger.info("Initializing database migration in {} from location {}",
                location, migrationTableName);
    }

    private void logPostMigration() {
        logger.info("Successfully performed database migrations");
    }
}
