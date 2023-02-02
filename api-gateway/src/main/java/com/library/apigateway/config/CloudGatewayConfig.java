package com.library.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Artur Tomeyan
 * @date 31/01/2023
 */
@Configuration
public class CloudGatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("book-service", r -> r.path("/api/v1/books")
                        .uri("lb://book-service"))
                .route("book-service", r -> r.path("/api/v1/authors")
                        .uri("lb://book-service"))
                .route("book-service", r -> r.path("/api/v1/publishers")
                        .uri("lb://book-service"))
                .route("book-service", r -> r.path("/api/v1/genres")
                        .uri("lb://book-service"))
                .route("user-registration-service", r -> r.path("/api/v1/register")
                        .uri("lb://user-registration-service"))
                .build();
    }
}