package com.mohsen.bankservice.service;


import com.mohsen.bankservice.controller.dto.AccountDto;
import com.mohsen.bankservice.model.entity.Account;

import java.math.BigDecimal;

public interface AccountService {

    Long addAccount(AccountDto accountDto);
    public Account cashDeposit(Account account, BigDecimal amount);
    public Account cashWithdrawal(Account account, BigDecimal amount);
}
