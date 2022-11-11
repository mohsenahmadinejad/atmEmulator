package com.mohsen.bankservice.controller;


import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.ReqTransactionDto;
import com.mohsen.bankservice.model.enums.AuthenticationMethodEnum;
import com.mohsen.bankservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;


@RestController
@RequestMapping("/api/cards")
@Validated
public class CardController {
    @Autowired
    private CardService cardService;


    @PostMapping
    public ResponseEntity<Long> addCard(@RequestBody CardDto cardDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.addCard(cardDto));
    }

    @PostMapping("/cash-deposit")
    public ResponseEntity<CardDto> cashDeposit(@RequestBody ReqTransactionDto reqTransactionDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cardService.cashDeposit(reqTransactionDto));
    }

    @PostMapping("/cash-withdrawal")
    public ResponseEntity<CardDto> cashWithdrawal(@RequestBody ReqTransactionDto reqTransactionDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cardService.cashWithdrawal(reqTransactionDto));
    }


    @GetMapping("/{cardNo}/check-balance")
    public ResponseEntity<BigDecimal> checkBalance(@PathVariable(name = "cardNo") String cardNo) {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.checkBalance(cardNo));
    }

    @PatchMapping("/{cardNo}/preferred-authentication-method/{method}")
    public ResponseEntity setPreferredAuthenticationMethod(@PathVariable(name = "cardNo") String cardNo,
                                                           @PathVariable(name = "method") AuthenticationMethodEnum method) {
        cardService.setPreferredAuthenticationMethod(cardNo,method);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}