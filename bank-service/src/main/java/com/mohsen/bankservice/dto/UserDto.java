package com.mohsen.bankservice.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserDto  {

    @Id
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

}
