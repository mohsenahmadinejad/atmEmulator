package com.mohsen.bankservice.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReqTransactionDto {
    private String cardNo;
    private BigDecimal amount;
}
