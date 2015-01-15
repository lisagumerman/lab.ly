package com.lab.ly.service;

import com.lab.ly.FileUploadService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by haswell on 1/13/15.
 */
@Configuration
public class ServiceConfiguration {

    @Bean
    public FileUploadService uploadService() {
        return new DefaultFileUploadService();
    }
}
