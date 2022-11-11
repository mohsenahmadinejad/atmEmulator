package com.mohsen.atmservice.controller.dto;

import com.mohsen.atmservice.model.enums.AuthenticationMethodEnum;
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
