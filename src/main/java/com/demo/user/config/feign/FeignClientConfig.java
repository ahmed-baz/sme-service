package com.demo.user.config.feign;

import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Value("${client.maxAttempts}")
    private int maxAttempts;
    @Value("${client.backoff}")
    private long backoff;

    @Bean
    public Retryer retryer() {
        return new FeignCustomRetryer(backoff, maxAttempts);
    }

}