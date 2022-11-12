package com.mohsen.atmservice.controller;



import com.mohsen.atmservice.controller.dto.ReqTransactionDto;
import com.mohsen.atmservice.controller.dto.ResCardDto;
import com.mohsen.atmservice.utils.HttpHeaderCreator;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;


@RestController
@RequestMapping("/api/card")
@Validated
public class CardController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Token token;

    @Value("${atm-emulator.bank-service.server}")
    private String bankServerUri;

    @Value("${atm-emulator.bank-service.server.cash-deposit}")
    private String cashDepositUri;

    @Value("${atm-emulator.bank-service.server.cash-withdrawal}")
    private String cashWithdrawalUri;

    @Value("${atm-emulator.bank-service.server.check-balance}")
    private String checkBalanceUri;

    @PostMapping("/cash-deposit")
    @CircuitBreaker(name = "bank-server-fallback", fallbackMethod = "fallbackMethod" )
    public ResponseEntity<ResCardDto> cashDeposit(@RequestBody ReqTransactionDto reqTransactionDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token.getToken());
        HttpEntity<ReqTransactionDto> request = new HttpEntity<ReqTransactionDto>(reqTransactionDto,
                HttpHeaderCreator.create(MediaType.APPLICATION_JSON, token));

        return restTemplate.exchange(bankServerUri +cashDepositUri,
                HttpMethod.POST,
                request,
                ResCardDto.class,
                new HashMap<>());    }

    @PostMapping("/cash-withdrawal")
    @CircuitBreaker(name = "bank-server-fallback", fallbackMethod = "fallbackMethod" )
    public ResponseEntity<ResCardDto> cashWithdrawal(@RequestBody ReqTransactionDto reqTransactionDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token.getToken());
        HttpEntity<ReqTransactionDto> request = new HttpEntity<ReqTransactionDto>(reqTransactionDto,
                HttpHeaderCreator.create(MediaType.APPLICATION_JSON, token));

        return restTemplate.exchange(bankServerUri +cashDepositUri,
                HttpMethod.POST,
                request,
                ResCardDto.class,
                new HashMap<>());
    }

    @GetMapping("/{cardNo}/check-balance")
    @CircuitBreaker(name = "bank-server-fallback", fallbackMethod = "fallbackMethod" )
    public ResponseEntity<BigDecimal> checkBalance(@PathVariable(name = "cardNo") String cardNo) {

        HttpEntity<ReqTransactionDto> request = new HttpEntity<ReqTransactionDto>(null,
                HttpHeaderCreator.create(MediaType.APPLICATION_JSON, token));

        return restTemplate.exchange(bankServerUri +String.format(checkBalanceUri,cardNo),
                HttpMethod.GET,
                request,
                BigDecimal.class,
                new HashMap<>());
    }


    public ResponseEntity<String>  fallbackMethod(Exception e)  {
        return ResponseEntity.ok("bank-server is not available");
    }


}