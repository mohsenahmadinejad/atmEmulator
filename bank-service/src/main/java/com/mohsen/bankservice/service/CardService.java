package com.mohsen.bankservice.service;




import com.mohsen.common.dto.AuthenticationMethodEnum;
import com.mohsen.common.dto.CardDto;
import com.mohsen.common.dto.ReqTransactionDto;
import com.mohsen.common.dto.ResCardDto;

import java.math.BigDecimal;

public interface CardService {

    Long addCard(CardDto cardDto);

    ResCardDto cashDeposit(ReqTransactionDto reqTransactionDto);
    ResCardDto cashWithdrawal(ReqTransactionDto reqTransactionDto);

    BigDecimal checkBalance(String cardNo);

    void setPreferredAuthenticationMethod(String cardNo, AuthenticationMethodEnum method);
}
