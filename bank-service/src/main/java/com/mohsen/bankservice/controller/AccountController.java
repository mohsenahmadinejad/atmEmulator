package com.mohsen.bankservice.controller;


import com.mohsen.bankservice.dto.AccountDto;
import com.mohsen.bankservice.repository.AccountRepository;
import com.mohsen.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<Long> addAccount(@RequestBody AccountDto accountDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(accountDto));
    }

}