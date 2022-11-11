package com.mohsen.bankservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Api( tags = "Clients")
@RestController
@RequestMapping("/wellcome")
public class WellcomeController {

    @Value("${welcome.message}")
    private String welcomeMessage;

    @GetMapping("/welcome")
    public ResponseEntity<String> sayHello() throws InterruptedException {
        Thread.sleep(6000);
        return ResponseEntity.ok(welcomeMessage) ;
    }


}
