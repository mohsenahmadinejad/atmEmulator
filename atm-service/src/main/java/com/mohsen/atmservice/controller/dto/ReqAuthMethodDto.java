package com.mohsen.atmservice.controller.dto;

import com.mohsen.atmservice.model.enums.AuthenticationMethodEnum;
import lombok.Data;

@Data
public class ReqAuthMethodDto {
    private String cardNo;
    private AuthenticationMethodEnum method;

}
