package com.mohsen.bankservice.service;


import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.UserDto;
import com.mohsen.bankservice.enums.AuthenticationMethodEnum;

import java.math.BigDecimal;

public interface CardService {

    Long addCard(CardDto cardDto);

    CardDto cashDeposit(String cardNo, BigDecimal amount);
    CardDto cashWithdrawal(String cardNo, BigDecimal amount);

    BigDecimal checkBalance(String cardNo);

    void setPreferredAuthenticationMethod(String cardNo, AuthenticationMethodEnum method);
}
