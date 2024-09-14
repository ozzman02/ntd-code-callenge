package com.ntd.filter;

import com.ntd.filter.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static com.ntd.constants.ApplicationConstants.API_ENDPOINTS;
import static com.ntd.constants.ApplicationConstants.BEARER;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> apiEndpoints = Arrays.asList(API_ENDPOINTS);

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!hasAuthorizationHeader(request)) {
                return onError(exchange);
            }
            String token = request.getHeaders()
                    .getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);

            if (token != null && token.startsWith(BEARER)) {
                token = token.substring(7);
            }

            try {
                jwtUtil.validateToken(token);
            } catch (Exception e) {
                return onError(exchange);
            }
        }
        return chain.filter(exchange);
    }


    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private boolean hasAuthorizationHeader(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

}
