package com.mohsen.atmservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCardDto {

    private String cardNo;
    private BigDecimal accountBalance;

}
