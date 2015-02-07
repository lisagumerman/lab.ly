package com.lab.ly.configurations.model;

import com.lab.ly.model.MigrationManager;
import com.lab.ly.model.PersistenceContext;
import com.lab.ly.model.internal.DatabaseMigrationTask;
import com.lab.ly.model.internal.DefaultMigrationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by haswell on 1/5/15.
 */

@Configuration
@EnableTransactionManagement
public class ModelConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            DataSource dataSource,
            JpaVendorAdapter adapter,
            Properties jpaProperties
    )  {
        final LocalContainerEntityManagerFactoryBean bean =
                new LocalContainerEntityManagerFactoryBean();

        bean.setJpaVendorAdapter(adapter);
        bean.setDataSource(dataSource);
        bean.setPackagesToScan("com.lab.ly.model");
        bean.setJpaProperties(jpaProperties);
        return bean;
    }


    @Bean
    public EntityManager entityManager(
            EntityManagerFactory entityManagerFactory
    ) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(
            EntityManagerFactory factory
    ) {
        return new JpaTransactionManager(factory);
    }

    @Bean
    public PersistenceContext defaultPersistenceContext(DataSource dataSource) {
        return new PersistenceContext(dataSource);
    }


    @Bean
    @Singleton
    @Named("migrationLocation")
    public String migrationLocation() {
        return "db.migrations.postgres";
    }

    @Bean
    @Singleton
    public MigrationManager migrationManager(
            @Named("migrationLocation") String migrationLocation,
            @Named("migrationTableName") String migrationTableName,
            PersistenceContext context
    ) {
        MigrationManager manager = new DefaultMigrationManager();
        manager.registerTask(new DatabaseMigrationTask(
                migrationLocation,
                migrationTableName
        ));
        manager.apply(context);
        return manager;
    }


}
