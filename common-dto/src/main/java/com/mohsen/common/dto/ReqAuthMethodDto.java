package com.mohsen.common.dto;

import com.mohsen.common.enums.AuthenticationMethodEnum;
import lombok.Data;

@Data
public class ReqAuthMethodDto {
    private String cardNo;
    private AuthenticationMethodEnum method;

}
