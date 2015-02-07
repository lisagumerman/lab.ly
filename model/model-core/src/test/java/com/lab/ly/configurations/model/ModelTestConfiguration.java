package com.lab.ly.configurations.model;

import com.lab.ly.configurations.model.ModelConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by haswell on 1/6/15.
 */
@Configuration
@Import(ModelConfiguration.class)
public class ModelTestConfiguration {

    @Bean
    @Primary
    public DataSource dataSource(final Properties persistenceProperties) {
        final HikariConfig config = getConfigurationFromProperties(persistenceProperties);
        return new HikariDataSource(config);
    }


    @Bean
    public JpaVendorAdapter vendorAdapter() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean
    @Singleton
    @Named("migrationLocation")
    public String migrationLocation() {
        return "db.migrations.hsqldb";
    }

    @Bean
    @Singleton
    @Named("migrationTableName")
    public String migrationTableName() {
        return "lably_migrations";
    }




    @Bean
    public Properties persistenceProperties() throws IOException {
        final Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("persistence.properties"));
        return properties;
    }

    private HikariConfig getConfigurationFromProperties(Properties persistenceProperties) {
        final HikariConfig config = new HikariConfig();

        config.setUsername(persistenceProperties
                .getProperty("jdbc.username"));

        config.setPassword(persistenceProperties
                .getProperty("jdbc.username"));

        config.setJdbcUrl(persistenceProperties
                .getProperty("jdbc.url"));

        loadJdbcDriver(persistenceProperties, config);
        return config;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty(
                "hibernate.hbm2ddl.auto",
                "none"
        );
        properties.setProperty(
                "hibernate.dialect",
                "org.hibernate.dialect.H2Dialect"
        );

        properties.setProperty(
                "hibernate.show_sql",
                "true"
        );
        return properties;
    }




    private void loadJdbcDriver(
            Properties persistenceProperties,
            HikariConfig config) {
        final String driverClass = (persistenceProperties
                        .getProperty("jdbc.driverClass"));
        try {
            Class.forName(driverClass);
            config.setDriverClassName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

    }
}
