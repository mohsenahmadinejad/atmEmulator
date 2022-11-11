package com.mohsen.atmservice.controller.dto;

import lombok.Data;

@Data
public class UserDto  {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

}
