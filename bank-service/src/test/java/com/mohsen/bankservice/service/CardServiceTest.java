package com.mohsen.bankservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohsen.bankservice.AtmTestCase;

import com.mohsen.bankservice.entity.Account;
import com.mohsen.bankservice.entity.Card;
import com.mohsen.bankservice.repository.AccountRepository;
import com.mohsen.bankservice.repository.AccountTransactionRepository;
import com.mohsen.bankservice.repository.CardRepository;
import com.mohsen.common.dto.ReqTransactionDto;
import com.mohsen.common.dto.ResCardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CardServiceTest {

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AccountTransactionRepository accountTransactionRepository;

    @MockBean
    private AccountService accountService;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Card card =
                objectMapper.readValue( AtmTestCase.readJsonCase(AtmTestCase.TEST_CASE_CARD_1) , Card.class);

        ReqTransactionDto reqTransactionDto =
                objectMapper.readValue( AtmTestCase.readJsonCase(AtmTestCase.TEST_CASE_REQ_TRANSACTION_DTO_1) ,
                        ReqTransactionDto.class);

        Mockito.when(cardRepository.findByCardNo("300")).thenReturn(card);

        Mockito.when(accountRepository.findById(card.getAccount().getId()))
                .thenReturn(Optional.ofNullable(card.getAccount()));


        Mockito.when(accountService.cashDeposit(card.getAccount(),new BigDecimal(10)))
               .thenReturn(Account.builder().
                accountNo(card.getAccount().getAccountNo()).
                balance(card.getAccount().getBalance().add(new BigDecimal(10))).
                build()
               ) ;

        Mockito.when(accountService.cashWithdrawal(card.getAccount(),new BigDecimal(10)))
                .thenReturn(Account.builder().
                        accountNo(card.getAccount().getAccountNo()).
                        balance(card.getAccount().getBalance().subtract(new BigDecimal(10))).
                        build()
                ) ;


    }

    @Test
    public void whenInputCardId_thenReturnBalance(){
        String cardNo="300";
        BigDecimal balance=new BigDecimal(150);
        BigDecimal foundBalance=cardService.checkBalance(cardNo);
        assertEquals(balance,foundBalance);
    }

    @Test
    public void whenDiposit_thenReturnNewBalance(){
        String cardNo="300";
        BigDecimal amount=new BigDecimal(10);
        BigDecimal newBalance=new BigDecimal(160);
        ResCardDto resCardDto=cardService.cashDeposit(new ReqTransactionDto(cardNo,amount));
        assertEquals(newBalance,resCardDto.getAccountBalance());
    }

    @Test
    public void whenWithdrawal_thenReturnNewBalance(){
        String cardNo="300";
        BigDecimal amount=new BigDecimal(10);
        BigDecimal newBalance=new BigDecimal(140);
        ResCardDto resCardDto=cardService.cashWithdrawal(new ReqTransactionDto(cardNo,amount));
        assertEquals(newBalance,resCardDto.getAccountBalance());
    }

}