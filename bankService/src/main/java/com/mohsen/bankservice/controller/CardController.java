package com.mohsen.bankservice.controller;


import com.mohsen.bankservice.dto.AccountDto;
import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.UserDto;
import com.mohsen.bankservice.security.entity.AuthRequest;
import com.mohsen.bankservice.security.util.JwtUtil;
import com.mohsen.bankservice.service.CardService;
import com.mohsen.bankservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    private CardService cardService;


    @PostMapping
    public ResponseEntity<Long> addCard(@RequestBody CardDto cardDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.addCard(cardDto));
    }


}