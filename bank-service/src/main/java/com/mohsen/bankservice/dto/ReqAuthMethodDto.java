package com.mohsen.bankservice.dto;

import com.mohsen.bankservice.model.enums.AuthenticationMethodEnum;
import lombok.Data;

@Data
public class ReqAuthMethodDto {
    private String cardNo;
    private AuthenticationMethodEnum method;

}
