package com.mohsen.bankservice.service;



import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.ReqTransactionDto;
import com.mohsen.bankservice.dto.ResCardDto;
import com.mohsen.bankservice.exception.NotPositiveAmountException;
import com.mohsen.bankservice.model.entity.Account;
import com.mohsen.bankservice.model.entity.Card;
import com.mohsen.bankservice.model.enums.AuthenticationMethodEnum;
import com.mohsen.bankservice.exception.CardNotFoundException;
import com.mohsen.bankservice.repository.AccountRepository;
import com.mohsen.bankservice.repository.CardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Long addCard(CardDto cardDto) {

        Card card = new Card();
        card=modelMapper.map(cardDto,Card.class);
        card.setFailedAuthenticateCount(0);
        card.setPin(passwordEncoder.encode(card.getPin()));
        card.setFingerPrint(passwordEncoder.encode(card.getFingerPrint()));
        return cardRepository.save(card).getId();
    }

    @Override
    public ResCardDto cashDeposit(ReqTransactionDto reqTransactionDto) {
        String cardNo=reqTransactionDto.getCardNo();
        BigDecimal amount=reqTransactionDto.getAmount();
        if(amount.intValue()<0){
            throw new NotPositiveAmountException("Not positive amount");
        }
        Card card= cardRepository.findByCardNo(cardNo);
        if (card == null ){
            throw new CardNotFoundException(String.format("Card not found by number: %s",cardNo));
        }
        Account account=accountRepository.findById(card.getAccount().getId()).get();
        account=accountService.cashDeposit(account,amount);
        ResCardDto resCardDto=new ResCardDto();
        resCardDto=modelMapper.map(card,ResCardDto.class);
        resCardDto.setAccountBalance(account.getBalance());
        return resCardDto;
    }

    @Override
    public ResCardDto cashWithdrawal(ReqTransactionDto reqTransactionDto) {
        String cardNo=reqTransactionDto.getCardNo();
        BigDecimal amount=reqTransactionDto.getAmount();
        if(amount.intValue()<0){
            throw new NotPositiveAmountException("Not positive amount");
        }
        Card card= cardRepository.findByCardNo(cardNo);
        if (card == null ){
            throw new CardNotFoundException(String.format("Card not found by number: %s",cardNo));
        }
        Account account=accountRepository.findById(card.getAccount().getId()).get();
        account=accountService.cashWithdrawal(account,amount);
        ResCardDto resCardDto=new ResCardDto();
        resCardDto=modelMapper.map(card,ResCardDto.class);
        resCardDto.setAccountBalance(account.getBalance());
        return resCardDto;
    }

    @Override
    public BigDecimal checkBalance(String cardNo) {
        Card card= cardRepository.findByCardNo(cardNo);
        if (card == null ){
            throw new CardNotFoundException(String.format("Card not found by number: %s",cardNo));
        }
        Account account=accountRepository.findById(card.getAccount().getId()).get();
        return account.getBalance();
    }
    public void setPreferredAuthenticationMethod(String cardNo, AuthenticationMethodEnum method){
        Card card= cardRepository.findByCardNo(cardNo);
        if (card == null ){
            throw new CardNotFoundException(String.format("Card not found by number: %s",cardNo));
        }
        card.setPreferredAuthenticationMethod(method);
        cardRepository.save(card);
        return ;
    }


}
