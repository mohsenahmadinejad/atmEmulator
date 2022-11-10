package com.mohsen.bankservice.service;


import com.mohsen.bankservice.dto.AccountDto;
import com.mohsen.bankservice.dto.CardDto;

public interface AccountService {

    Long addAccount(AccountDto accountDto);

}
