package com.example.paymenttest.controller;

import com.example.paymenttest.entity.PaymentCommission;
import com.example.paymenttest.service.PaymentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {
    List<PaymentCommission> paymentCommissions = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    private PaymentCommission paymentCommission1;
    private PaymentCommission paymentCommission2;

    private Date date1;
    private Date date2;
    @BeforeEach
    void setUp() {
        date1 = Date.from(Instant.now());


        date2 = Date.from(Instant.now());
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate2 = dateFormat2.format(date2);

        paymentCommission1 = new PaymentCommission(1L,"Nadya",7000L,date1,"1%");
        paymentCommission2 = new PaymentCommission(2L,"Nadya1111",50000L,date2,"3%");
        paymentCommissions.add(paymentCommission1);
        paymentCommissions.add(paymentCommission2);


    }

    @AfterEach
    void tearDown() {
        paymentCommission1 = null;
        paymentCommission2 = null;
        paymentCommissions.clear();
        date1 = null;
        date2 = null;

    }

    @Test
    void newSave() {

    }

    @Test
    void listByAll() throws Exception {
        when(paymentService.findAll()).thenReturn(paymentCommissions);

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = dateFormat1.format(date1);

        mockMvc.perform(get("/payment/findAll"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$" ,hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].client",is("Nadya")))
                .andExpect(jsonPath("$[0].sumPayment",is(7000)))
                .andExpect(jsonPath("$[0].datePayment", is(strDate1)))
                .andExpect(jsonPath("$[0].comment", is("1%")));

    }

    @Test
    void newListDto() {
    }

    @Test
    void newListSumDTO() {
    }

    @Test
    void findByDate() {
    }

    @Test
    void findByDateAfter() {
    }

    @Test
    void findByDateBefore() {
    }
}