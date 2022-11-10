package com.mohsen.bankservice.service;



import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.dto.UserDto;
import com.mohsen.bankservice.entity.Card;
import com.mohsen.bankservice.entity.User;
import com.mohsen.bankservice.repository.CardRepository;
import com.mohsen.bankservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardRepository cardRepository;

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
        return cardRepository.save(card).getId();
    }


}
