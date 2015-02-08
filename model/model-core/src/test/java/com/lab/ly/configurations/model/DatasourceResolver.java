package com.lab.ly.configurations.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by haswell on 2/7/15.
 */
public class DatasourceResolver {

    private DatasourceResolver() {
    }

    public static DataSource resolveDataSource() {
        final HikariConfig config = getConfigurationFromProperties();
        return new HikariDataSource(config);
    }


    public static Properties persistenceProperties() throws IOException {
        final Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("persistence.properties"));
        return properties;
    }

    private static HikariConfig getConfigurationFromProperties() {

        try {
            final Properties persistenceProperties = persistenceProperties();
            final HikariConfig config = new HikariConfig();

            config.setUsername(persistenceProperties
                    .getProperty("jdbc.username"));

            config.setPassword(persistenceProperties
                    .getProperty("jdbc.username"));

            config.setJdbcUrl(persistenceProperties
                    .getProperty("jdbc.url"));

            loadJdbcDriver(persistenceProperties, config);
            return config;

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
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


    private static void loadJdbcDriver(
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
