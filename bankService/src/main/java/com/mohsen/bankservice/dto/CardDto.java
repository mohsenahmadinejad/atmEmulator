package com.mohsen.bankservice.dto;

import com.mohsen.bankservice.entity.Account;
import com.mohsen.bankservice.entity.Card;
import com.mohsen.bankservice.enums.AuthenticationMethodEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CardDto {


    private Long id;
    private String cardNo;
    private String pin;
    private String fingerPrint;
    private String firstName;
    private String lastName;
    private AuthenticationMethodEnum preferredAuthenticationMethod;
    private BigDecimal accountBalance;


}
