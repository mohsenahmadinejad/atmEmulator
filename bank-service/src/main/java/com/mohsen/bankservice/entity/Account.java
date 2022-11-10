package com.mohsen.bankservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends Auditable<String> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNo;
    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="FK_ACCOUNT_ID",referencedColumnName = "id")
    private List<Card> cardList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="FK_ACCOUNT_ID",referencedColumnName = "id")
    private List<AccountTransaction> accountTransactionList;

    @Version
    private Long version;
}
