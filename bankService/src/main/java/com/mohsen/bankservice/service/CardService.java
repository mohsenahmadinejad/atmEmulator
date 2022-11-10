package com.mohsen.bankservice.service;


import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.UserDto;

import java.math.BigDecimal;

public interface CardService {

    Long addCard(CardDto cardDto);

    CardDto cashDeposit(CardDto cardDto, BigDecimal amount);
    CardDto cashWithdrawal(CardDto cardDto, BigDecimal amount);

}
