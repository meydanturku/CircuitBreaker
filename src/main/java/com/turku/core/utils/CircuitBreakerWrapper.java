package com.turku.core.utils;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class CircuitBreakerWrapper {

    private final CircuitBreaker circuitBreaker;

    public CircuitBreakerWrapper() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(5)
                .build();
        this.circuitBreaker = CircuitBreaker.of("defaultCircuitBreaker", config);
    }

    public <T> T run(Supplier<T> supplier, Function<Throwable, T> fallback) {
        try {
            return circuitBreaker.executeSupplier(supplier);
        } catch (Exception e) {
            return fallback.apply(e);
        }
    }
}