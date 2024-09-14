package com.ntd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;

import static com.ntd.constants.ApplicationConstants.*;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI(@Value("${application-title}") String title,
                           @Value("${application-description}") String description,
                           @Value("${application-version}") String version,
                           @Value("${application-license}") String license) {
        return new OpenAPI().addSecurityItem(new SecurityRequirement()
                        .addList(BEARER_AUTHENTICATION))
                .components(new Components().addSecuritySchemes
                        (BEARER_AUTHENTICATION, createAPIKeyScheme()))
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                        .license(new License().name(license)));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat(JWT_FORMAT)
                .scheme(BEARER_SCHEME);
    }

}
