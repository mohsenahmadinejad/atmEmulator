package com.mohsen.common.dto;


import com.mohsen.common.enums.AuthenticationMethodEnum;
import lombok.Data;

import java.math.BigDecimal;

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
