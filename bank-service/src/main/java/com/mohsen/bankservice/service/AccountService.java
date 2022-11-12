package com.mohsen.bankservice.service;


import com.mohsen.bankservice.entity.Account;
import com.mohsen.common.dto.AccountDto;

import java.math.BigDecimal;

public interface AccountService {

    Long addAccount(AccountDto accountDto);
    public Account cashDeposit(Account account, BigDecimal amount);
    public Account cashWithdrawal(Account account, BigDecimal amount);
}
