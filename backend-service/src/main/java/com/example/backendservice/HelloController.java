package com.example.backendservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String applicationName;
    private final int serverPort;

    public HelloController(@Value("${spring.application.name}") String applicationName,
                           @Value("${server.port}") int serverPort) {
        this.applicationName = applicationName;
        this.serverPort = serverPort;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from " + applicationName + ":" + serverPort;
    }
}
