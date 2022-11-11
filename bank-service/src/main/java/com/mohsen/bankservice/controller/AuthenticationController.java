package com.mohsen.bankservice.controller;


import com.mohsen.bankservice.dto.CardDto;
import com.mohsen.bankservice.exception.CardIsBlockedException;
import com.mohsen.bankservice.exception.UntAuthorizedException;
import com.mohsen.bankservice.model.entity.Card;
import com.mohsen.bankservice.repository.CardRepository;
import com.mohsen.bankservice.security.entity.AuthRequest;
import com.mohsen.bankservice.security.util.JwtUtil;
import com.mohsen.bankservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping()
public class AuthenticationController {

    @Autowired
    private CardService cardService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CardRepository cardRepository;


    @PostMapping("/signIn")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        Card card = cardRepository.findByCardNo(authRequest.getCardNumber());
        if (card==null){
            throw new UntAuthorizedException("invalid card number/password");
        }else{
            if(card.getFailedAuthenticateCount()>3){
                throw new CardIsBlockedException("Card is blocked");
            }
        }


        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getCardNumber(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            card.setFailedAuthenticateCount(card.getFailedAuthenticateCount()+1);
            cardRepository.save(card);
            throw new UntAuthorizedException("invalid card number/password");
        }
        return jwtUtil.generateToken(authRequest.getCardNumber());
    }

    @PostMapping("/signUp")
    public ResponseEntity<Long> addCard(@RequestBody CardDto cardDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.addCard(cardDto));
    }
}