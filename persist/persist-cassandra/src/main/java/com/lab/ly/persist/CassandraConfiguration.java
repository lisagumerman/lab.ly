package com.lab.ly.persist;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by haswell on 1/17/15.
 */
@Configuration
@ComponentScan(basePackageClasses =
        CassandraConfiguration.class)
public class CassandraConfiguration {
}
