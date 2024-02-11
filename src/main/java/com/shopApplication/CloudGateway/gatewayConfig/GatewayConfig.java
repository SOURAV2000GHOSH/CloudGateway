package com.shopApplication.CloudGateway.gatewayConfig;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {
    @Bean
    KeyResolver useKeyResolver(){
        return exchange -> Mono.just("userKey");
    }
    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCutomizer(){
        return factory -> factory.configureDefault(
                id-> new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(
                                CircuitBreakerConfig.ofDefaults()
                        ).build()
        );
    }
}
