package com.ntd.configuration;

import com.ntd.feign.ApplicationErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() { return new ApplicationErrorDecoder(); }

}
