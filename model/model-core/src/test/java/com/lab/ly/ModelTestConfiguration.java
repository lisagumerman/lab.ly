package com.lab.ly;

import com.lab.ly.model.ModelConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

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
    public DataSource dataSource(final Properties persistenceProperties) {
        final HikariConfig config = getConfigurationFromProperties(persistenceProperties);
        return new HikariDataSource(config);
    }


    @Bean
    public JpaVendorAdapter vendorAdapter() {
        return new HibernateJpaVendorAdapter();
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


    @Singleton
    @Bean(initMethod = "migrate")
    public Flyway migrator(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setLocations("db.migrations.hsqldb");
        flyway.setDataSource(dataSource);
        return flyway;
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
