package com.mohsen.bankservice.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohsen.bankservice.AtmTestCase;
import com.mohsen.bankservice.entity.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setup() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Card card =
                objectMapper.readValue( AtmTestCase.readJsonCase(AtmTestCase.TEST_CASE_CARD_1) , Card.class);

    entityManager.persist(card);
    }


    @Test
    public void whenFindByCardNo_thenReturnCard(){
        Card card=cardRepository.findByCardNo("300");
        assertEquals(card.getCardNo(),"mohsen");

    }


}