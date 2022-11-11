package com.mohsen.bankservice.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResCardDto {

    private String cardNo;
    private BigDecimal accountBalance;

}
