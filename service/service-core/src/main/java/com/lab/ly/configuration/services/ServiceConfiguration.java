package com.lab.ly.configuration.services;

import com.lab.ly.configurations.model.ModelConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by haswell on 1/13/15.
 */
@Import({
    ModelConfiguration.class
})
@Configuration
@EnableTransactionManagement
public class ServiceConfiguration {



}
