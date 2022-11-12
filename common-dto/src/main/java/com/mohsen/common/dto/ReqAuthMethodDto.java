package com.mohsen.common.dto;

import lombok.Data;

@Data
public class ReqAuthMethodDto {
    private String cardNo;
    private AuthenticationMethodEnum method;

}
