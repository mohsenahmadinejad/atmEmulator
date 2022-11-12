package com.mohsen.bankservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mohsen.common.enums.AuthenticationMethodEnum;
import lombok.*;

import javax.persistence.*;

@Builder
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
    private Integer failedAuthenticateCount;

    @Enumerated(EnumType.ORDINAL)
    private AuthenticationMethodEnum preferredAuthenticationMethod;

    @ManyToOne
    @JoinColumn(name = "FK_ACCOUNT_ID",referencedColumnName ="ID" )
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AuthenticationMethodEnum getPreferredAuthenticationMethod() {
        return preferredAuthenticationMethod;
    }

    public void setPreferredAuthenticationMethod(AuthenticationMethodEnum preferredAuthenticationMethod) {
        this.preferredAuthenticationMethod = preferredAuthenticationMethod;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getFailedAuthenticateCount() {
        return failedAuthenticateCount;
    }

    public void setFailedAuthenticateCount(Integer failedAuthenticateCount) {
        this.failedAuthenticateCount = failedAuthenticateCount;
    }
}
