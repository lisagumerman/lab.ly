package com.lab.ly.configuration.services;

import com.lab.ly.configurations.model.DatasourceResolver;
import com.lab.ly.configurations.model.ModelTestConfiguration;
import com.lab.ly.configuration.services.ServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * Created by haswell on 1/13/15.
 */
@Configuration
@Import({
    ModelTestConfiguration.class,
    ServiceConfiguration.class
})
public class ServiceTestConfiguration {

    @Bean
    public DataSource dataSource() {
        return DatasourceResolver.resolveDataSource();
    }


}
