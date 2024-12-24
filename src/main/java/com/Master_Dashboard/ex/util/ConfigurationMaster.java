package com.Master_Dashboard.ex.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigurationMaster {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
   
    @Bean
    public SetErrorResponses setErrorResponses() {
        return new SetErrorResponses();
    }

//    transactionReportController
//    @Bean
//    public SetErrorResponses setErrorResponses() {
//        return new SetErrorResponses();
//    }
}