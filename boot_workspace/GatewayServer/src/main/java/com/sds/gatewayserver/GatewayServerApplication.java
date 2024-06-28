package com.sds.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {

	
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
        		
        	// "/xxxxx" 프리픽스를 제거
	        .route("movieapp_app", r -> r.path("/movieapp/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:7777"))
	        //.route("movieapp_app", r -> r.path("/movieapp/**").filters(f -> f.stripPrefix(1)).uri("http://223.130.154.244:7777"))
	        //.route("movieapp_static_resources", r -> r.path("/static/**").uri("http://localhost:7777"))
	        
        	//.route("recommend_app", r -> r.path("/recommend/**").filters(f -> f.stripPrefix(1)).uri("http://223.130.154.244:8282"))
        	.route("recommend_app", r -> r.path("/recommend/**").filters(f -> f.stripPrefix(1)).uri("http://localhost:8282"))
        	//.route("recommend_static_resources", r -> r.path("/static/**").uri("http://localhost:8282"))        	
        	.build();
    }	
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

}
