package com.mohsen.bankservice.controller;


import com.mohsen.bankservice.dto.AccountDto;
import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.UserDto;
import com.mohsen.bankservice.enums.AuthenticationMethodEnum;
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

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
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

    @PutMapping("/cash-deposit/{cardNo}/{amount}")
    public ResponseEntity<CardDto> cashDeposit(@PathVariable(name = "cardNo") String cardNo,
                                               @PathVariable(name = "amount") @Positive BigDecimal amount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.cashDeposit(cardNo,amount));
    }

    @PutMapping("/cash-withdrawal/{cardNo}/{amount}")
    public ResponseEntity<CardDto> cashWithdrawal(@PathVariable(name = "cardNo") String cardNo,
                                                  @PathVariable(name = "amount") @Positive BigDecimal amount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.cashWithdrawal(cardNo,amount));
    }


    @GetMapping("/check-balance/{cardNo}")
    public ResponseEntity<BigDecimal> checkBalance(@PathVariable(name = "cardNo") String cardNo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.checkBalance(cardNo));
    }

    @PatchMapping("/preferred-authentication-method/{cardNo}/{method}")
    public ResponseEntity setPreferredAuthenticationMethod(@PathVariable(name = "cardNo") String cardNo,
                                                 @PathVariable(name = "method") AuthenticationMethodEnum method) {
        cardService.setPreferredAuthenticationMethod(cardNo,method);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}