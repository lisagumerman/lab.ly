package com.lab.ly.service;

import com.lab.ly.ModelTestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by haswell on 1/13/15.
 */
@Configuration
@Import({
    ServiceConfiguration.class,
    ModelTestConfiguration.class
})
public class ServiceTestConfiguration {


}
