package com.mohsen.bankservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mohsen.bankservice.enums.AuthenticationMethodEnum;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String cardNo;
    private String pin;
    private String fingerPrint;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    private AuthenticationMethodEnum preferredAuthenticationMethod;

    @ManyToOne
    @JoinColumn(name = "FK_ACCOUNT_ID",referencedColumnName ="ID" )
    private Account account;

}
