package com.mohsen.bankservice.service;


import com.mohsen.bankservice.controller.dto.CardDto;
import com.mohsen.bankservice.controller.dto.ReqTransactionDto;
import com.mohsen.bankservice.controller.dto.ResCardDto;
import com.mohsen.bankservice.model.enums.AuthenticationMethodEnum;

import java.math.BigDecimal;

public interface CardService {

    Long addCard(CardDto cardDto);

    ResCardDto cashDeposit(ReqTransactionDto reqTransactionDto);
    ResCardDto cashWithdrawal(ReqTransactionDto reqTransactionDto);

    BigDecimal checkBalance(String cardNo);

    void setPreferredAuthenticationMethod(String cardNo, AuthenticationMethodEnum method);
}
