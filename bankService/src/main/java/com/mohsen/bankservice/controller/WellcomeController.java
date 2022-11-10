package com.mohsen.bankservice.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private RestTemplate restTemplate;



    @GetMapping("/hello")
    @CircuitBreaker(name = "helloService", fallbackMethod = "helloFallback" )
    public ResponseEntity<String> sayHello() throws InterruptedException {
        String response = restTemplate.getForObject("http://localhost:8081/wellcome/hello3", String.class);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/hello2")
    public ResponseEntity<String> sayHello2() throws InterruptedException {
//        Thread.sleep(6000);
        return ResponseEntity.ok("Hi..") ;
    }


    public ResponseEntity<String>  helloFallback(Exception e)  {
        return ResponseEntity.ok("The hello api is not accessible ..");
    }

}



