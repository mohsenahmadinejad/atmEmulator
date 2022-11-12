package com.mohsen.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResCardDto {

    private String cardNo;
    private BigDecimal accountBalance;

}
