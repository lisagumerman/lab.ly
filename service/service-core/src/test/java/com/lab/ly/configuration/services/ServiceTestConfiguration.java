package com.lab.ly.configuration.services;

import com.lab.ly.configurations.model.ModelTestConfiguration;
import com.lab.ly.configuration.services.ServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by haswell on 1/13/15.
 */
@Configuration
@Import({
    ModelTestConfiguration.class,
    ServiceConfiguration.class
})
public class ServiceTestConfiguration {


}
