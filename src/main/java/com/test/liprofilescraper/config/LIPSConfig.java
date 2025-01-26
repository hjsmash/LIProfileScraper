package com.test.liprofilescraper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LIPSConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
