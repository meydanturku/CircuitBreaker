package com.turku.core.utils;

import com.turku.core.service.MernisService;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ServiceInvoker {
    private final CircuitBreakerWrapper circuitBreakerWrapper;

    public ServiceInvoker(CircuitBreakerWrapper circuitBreakerWrapper) {
        this.circuitBreakerWrapper = circuitBreakerWrapper;
    }

    public String invokeWithCircuitBreaker(MernisService service, Function<Throwable, String> fallback) {

        return circuitBreakerWrapper.run(() -> {
            try {
                return service.hello();
            } catch (Exception e) {
                return fallback.apply(e);
            }
        }, fallback);
    }
}
