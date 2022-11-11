package com.mohsen.bankservice.controller;


import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.ReqAuthMethodDto;
import com.mohsen.bankservice.dto.ReqTransactionDto;
import com.mohsen.bankservice.dto.ResCardDto;
import com.mohsen.bankservice.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/card")
@Validated
@ApiOperation("Cards API")
public class CardController {
    @Autowired
    private CardService cardService;

    @ApiOperation(value = "Add a card", response = Long.class)
    @PostMapping
    public ResponseEntity<Long> addCard(@RequestBody CardDto cardDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.addCard(cardDto));
    }

    @ApiOperation(value = "Deposit amount from the account connected to card",response = ResCardDto.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Not positive amount"),
            @ApiResponse(code = 404, message = "Card not found") })
    @PostMapping("/cash-deposit")
    public ResponseEntity<ResCardDto> cashDeposit(@RequestBody ReqTransactionDto reqTransactionDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cardService.cashDeposit(reqTransactionDto));
    }

    @ApiOperation(value = "Withdrawal amount from the account connected to card", response = ResCardDto.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Not positive amount"),
            @ApiResponse(code = 404, message = "Card not found") })
    @PostMapping("/cash-withdrawal")
    public ResponseEntity<ResCardDto> cashWithdrawal(@RequestBody ReqTransactionDto reqTransactionDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cardService.cashWithdrawal(reqTransactionDto));
    }

    @ApiOperation(value = "Returns the balance of the account connected to card",response = BigDecimal.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Card not found") })
    @GetMapping("/{cardNo}/check-balance")
    public ResponseEntity<BigDecimal> checkBalance(@PathVariable(name = "cardNo") String cardNo) {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.checkBalance(cardNo));
    }

    @ApiOperation(value = "Set preferred authentication method to card")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Card not found") })
    @PutMapping("/preferred-authentication-method")
    public ResponseEntity setPreferredAuthenticationMethod(@RequestBody ReqAuthMethodDto reqAuthMethodDto) {

        cardService.setPreferredAuthenticationMethod(reqAuthMethodDto.getCardNo(),reqAuthMethodDto.getMethod());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}