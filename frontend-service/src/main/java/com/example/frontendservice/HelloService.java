package com.example.frontendservice;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private final RestTemplate restTemplate;
    private final String backendUri;
    private final CircuitBreaker circuitBreaker;

    public HelloService(RestTemplate restTemplate,
                        @Value("${backend.uri}") String backendUri,
                        CircuitBreakerRegistry circuitBreakerRegistry) {
        this.restTemplate = restTemplate;
        this.backendUri = backendUri;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("backend-service");
    }

    public String hello() {
        CheckedFunction0<String> decoratedSupplier = circuitBreaker.decorateCheckedSupplier(() -> restTemplate.getForObject(backendUri + "/hello", String.class));
        Try<String> result = Try.of(decoratedSupplier).recover(throwable -> "Recovered");
        return result.get();
    }
}
