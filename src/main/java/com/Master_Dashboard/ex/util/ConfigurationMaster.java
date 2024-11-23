package com.Master_Dashboard.ex.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.Master_Dashboard.repository.MerchantsRepository;


@Configuration
public class ConfigurationMaster {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
   
}