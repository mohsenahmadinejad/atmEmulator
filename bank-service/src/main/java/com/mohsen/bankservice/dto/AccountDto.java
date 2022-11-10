package com.mohsen.bankservice.dto;

import com.mohsen.bankservice.enums.AuthenticationMethodEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {


    private Long id;
    private String accountNo;
    private BigDecimal balance;
    private Long version;
}
