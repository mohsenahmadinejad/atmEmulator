package com.mohsen.bankservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wellcome")
public class WellcomeController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() throws InterruptedException {
        return ResponseEntity.ok("Hi..") ;
    }

}
