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
    public DataSource dataSource() {
        return DatasourceResolver.resolveDataSource();
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


}
