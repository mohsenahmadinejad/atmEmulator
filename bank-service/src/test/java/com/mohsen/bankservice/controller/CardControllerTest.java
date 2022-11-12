package com.mohsen.bankservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohsen.bankservice.AtmTestCase;

import com.mohsen.bankservice.entity.Card;
import com.mohsen.bankservice.repository.CardRepository;
import com.mohsen.bankservice.service.CardService;
import com.mohsen.common.dto.CardDto;
import com.mohsen.common.dto.ReqTransactionDto;
import com.mohsen.common.dto.ResCardDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class CardControllerTest {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;
    @MockBean
    private CardRepository cardRepository;
    private Card card;

    @BeforeEach
    void setup() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        card = objectMapper.readValue( AtmTestCase.readJsonCase(AtmTestCase.TEST_CASE_CARD_1) , Card.class);

    }

    @Test
    void addCard() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Card inputCard =
                objectMapper.readValue( AtmTestCase.readJsonCase(AtmTestCase.TEST_CASE_CARD_1) , Card.class);

        CardDto cardDto =modelMapper.map(inputCard, CardDto.class);
        String json=objectMapper.writeValueAsString(cardDto);

        inputCard.setId(null);
//        Mockito.when(cardService.addCard((cardDto)))
//                .thenReturn(card.getId());
        mockMvc.perform(post("/api/card")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void whenCheckBalance_thenReturnbalanceOfAccount() throws Exception {

        MvcResult mvcResult= mockMvc.perform(get("/api/card/200/check-balance")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        BigDecimal balance=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),BigDecimal.class);
        assertEquals(balance,card.getAccount().getBalance());


    }

    @Test
    void whenCashDeposit_theDepositToAccount() throws Exception {
        ReqTransactionDto reqTransactionDto=new ReqTransactionDto("200",new BigDecimal(50));

        ObjectMapper objectMapper = new ObjectMapper();
        String json=objectMapper.writeValueAsString(reqTransactionDto);

        MvcResult mvcResult=mockMvc.perform(post("/api/card/cash-deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isAccepted()).andReturn();
        ResCardDto resCardDto=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ResCardDto.class);

        assertEquals(reqTransactionDto.getCardNo(),resCardDto.getCardNo());
        assertEquals(reqTransactionDto.getAmount().add(new BigDecimal(50)),resCardDto.getAccountBalance());
    }

    @Test
    void whenCashWithdrawalWithEnoughAmount_theWithdrawalFromAccount() throws Exception {
        ReqTransactionDto reqTransactionDto=new ReqTransactionDto("200",new BigDecimal(50));

        ObjectMapper objectMapper = new ObjectMapper();
        String json=objectMapper.writeValueAsString(reqTransactionDto);

        MvcResult mvcResult=mockMvc.perform(post("/api/card/cash-withdrawal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isAccepted()).andReturn();
        ResCardDto resCardDto=objectMapper.readValue(mvcResult.getResponse().getContentAsString(),ResCardDto.class);

        assertEquals(reqTransactionDto.getCardNo(),resCardDto.getCardNo());
        assertEquals(reqTransactionDto.getAmount().subtract(new BigDecimal(50)),resCardDto.getAccountBalance());
    }

    @Test
    void whenCashWithdrawalWithNotEnoughAmount_theWithdrawalFromAccount() throws Exception {
        ReqTransactionDto reqTransactionDto=new ReqTransactionDto("200",new BigDecimal(5000));

        ObjectMapper objectMapper = new ObjectMapper();
        String json=objectMapper.writeValueAsString(reqTransactionDto);

        mockMvc.perform(post("/api/card/cash-withdrawal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

}