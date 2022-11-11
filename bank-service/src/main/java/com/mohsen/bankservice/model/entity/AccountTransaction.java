package com.mohsen.bankservice.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mohsen.bankservice.model.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountTransaction extends  Auditable<String> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TransactionType transactionType;
    private BigDecimal amount;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "FK_ACCOUNT_ID",referencedColumnName ="ID" )
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
