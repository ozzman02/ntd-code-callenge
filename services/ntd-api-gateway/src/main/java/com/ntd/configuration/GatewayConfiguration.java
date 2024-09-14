package com.ntd.configuration;

import com.ntd.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ntd.constants.ApplicationConstants.*;

@Configuration
@RequiredArgsConstructor
public class GatewayConfiguration {

    private final JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(AUTHORIZATION_SERVICE, r -> r.path(AUTHORIZATION_SERVICE_ROUTE_PATH)
                        .uri(AUTHORIZATION_SERVICE_URI))
                .route(USER_SERVICE, r -> r.path(USER_SERVICE_ROUTE_PATH)
                        .filters(f -> f.filter(filter))
                        .uri(USER_SERVICE_URI))
                .route(CALCULATOR_SERVICE, r -> r.path(CALCULATOR_SERVICE_ROUTE_PATH)
                        .filters(f -> f.filter(filter))
                        .uri(CALCULATOR_SERVICE_URI))
                .build();
    }

}
