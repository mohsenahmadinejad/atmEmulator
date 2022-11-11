package com.mohsen.bankservice.dto;

import com.mohsen.bankservice.model.enums.AuthenticationMethodEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResCardDto {

    private String cardNo;
    private BigDecimal accountBalance;

}
