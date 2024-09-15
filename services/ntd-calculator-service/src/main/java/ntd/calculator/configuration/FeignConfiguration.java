package ntd.calculator.configuration;

import feign.codec.ErrorDecoder;
import ntd.calculator.feign.ApplicationErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() { return new ApplicationErrorDecoder(); }

}
