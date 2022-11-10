package com.mohsen.bankservice.service;



import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.UserDto;
import com.mohsen.bankservice.entity.Account;
import com.mohsen.bankservice.entity.Card;
import com.mohsen.bankservice.entity.User;
import com.mohsen.bankservice.enums.AuthenticationMethodEnum;
import com.mohsen.bankservice.exception.CardNotFoundException;
import com.mohsen.bankservice.repository.AccountRepository;
import com.mohsen.bankservice.repository.CardRepository;
import com.mohsen.bankservice.repository.UserRepository;
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
//        User user = new User();
//        user=modelMapper.map(userDto,User.class);
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user).getId();

        Card card = new Card();
        card=modelMapper.map(cardDto,Card.class);
        card.setPin(passwordEncoder.encode(card.getPin()));
        card.setFingerPrint(passwordEncoder.encode(card.getFingerPrint()));
        return cardRepository.save(card).getId();
    }

    @Override
    public CardDto cashDeposit(String cardNo, BigDecimal amount) {
        Card card= cardRepository.findByCardNo(cardNo);
        if (card == null ){
            throw new CardNotFoundException(String.format("Can not find Card by number: %s",cardNo));
        }
        Account account=accountRepository.findById(card.getAccount().getId()).get();
        account=accountService.cashDeposit(account,amount);
        CardDto cardDto=new CardDto();
        cardDto=modelMapper.map(card,CardDto.class);
        cardDto.setAccountBalance(account.getBalance());
        return cardDto;
    }

    @Override
    public CardDto cashWithdrawal(String cardNo, BigDecimal amount) {
        Card card= cardRepository.findByCardNo(cardNo);
        if (card == null ){
            throw new CardNotFoundException(String.format("Can not find Card by number: %s",cardNo));
        }
        Account account=accountRepository.findById(card.getAccount().getId()).get();
        account=accountService.cashWithdrawal(account,amount);
        CardDto cardDto=new CardDto();
        cardDto=modelMapper.map(card,CardDto.class);
        cardDto.setAccountBalance(account.getBalance());
        return cardDto;
    }

    @Override
    public BigDecimal checkBalance(String cardNo) {
        Card card= cardRepository.findByCardNo(cardNo);
        if (card == null ){
            throw new CardNotFoundException(String.format("Can not find Card by number: %s",cardNo));
        }
        Account account=accountRepository.findById(card.getAccount().getId()).get();
        return account.getBalance();
    }
    public void setPreferredAuthenticationMethod(String cardNo, AuthenticationMethodEnum method){
        Card card= cardRepository.findByCardNo(cardNo);
        if (card == null ){
            throw new CardNotFoundException(String.format("Can not find Card by number: %s",cardNo));
        }
        card.setPreferredAuthenticationMethod(method);
        cardRepository.save(card);
        return ;
    }


}
