package com.mohsen.bankservice.security.service;



import com.mohsen.bankservice.entity.Card;
import com.mohsen.bankservice.entity.User;
import com.mohsen.bankservice.enums.AuthenticationMethodEnum;
import com.mohsen.bankservice.repository.CardRepository;
import com.mohsen.bankservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private CardRepository cardRepository;

//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = repository.findByUserName(username);
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
//    }


    @Override
    public UserDetails loadUserByUsername(String cardNo) throws UsernameNotFoundException {

        Card card = cardRepository.findByCardNo(cardNo);
        String cardPassword;
        if(card.getPreferredAuthenticationMethod()== AuthenticationMethodEnum.FINGER_PRINT){
            cardPassword=card.getFingerPrint();
        }else{
            cardPassword=card.getPin();
        }
        return new org.springframework.security.core.userdetails.User(card.getCardNo(), cardPassword, new ArrayList<>());
    }
}
