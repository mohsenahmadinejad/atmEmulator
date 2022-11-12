package com.mohsen.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {


    private Long id;
    private String accountNo;
    private BigDecimal balance;
    private Long version;
}
