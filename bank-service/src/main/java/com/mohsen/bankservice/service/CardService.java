package com.mohsen.bankservice.service;


import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.ReqTransactionDto;
import com.mohsen.bankservice.model.enums.AuthenticationMethodEnum;

import java.math.BigDecimal;

public interface CardService {

    Long addCard(CardDto cardDto);

    CardDto cashDeposit(ReqTransactionDto reqTransactionDto);
    CardDto cashWithdrawal(ReqTransactionDto reqTransactionDto);

    BigDecimal checkBalance(String cardNo);

    void setPreferredAuthenticationMethod(String cardNo, AuthenticationMethodEnum method);
}
