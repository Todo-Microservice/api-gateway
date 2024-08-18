package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

	@Bean
	RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(r -> r.path("/t/**")
						.filters(f -> f.rewritePath("/t/(?<segment>.*)", "/todo/${segment}")
								.circuitBreaker(c -> c.setName("gatewayCB")))
						.uri("lb://TODOSERVICE"))
				.build();
	}
}
