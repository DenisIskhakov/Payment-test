package com.example.paymenttest.service;

import com.example.paymenttest.DTO.PaymentDTO;
import com.example.paymenttest.DTO.SumDTO;
import com.example.paymenttest.entity.PaymentCommission;
import com.example.paymenttest.entity.SumCommition;
import com.example.paymenttest.repository.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    List<PaymentCommission> paymentCommissions = new ArrayList();
    // List = sumDTO
    List<SumDTO> sumDTOList = new ArrayList<>();
    //List = paymentDTO
    List<PaymentDTO> paymentDTOList = new ArrayList<>();



    @InjectMocks
    private PaymentService paymentService;
    @Mock
    private PaymentRepository paymentRepository;

    private PaymentCommission paymentCommission1;
    private PaymentCommission paymentCommission2;
    private PaymentCommission paymentCommission3;

    private SumCommition sumCommition;

    private SumDTO sumDTO;
    private PaymentDTO paymentDTO;

    @BeforeEach
    void setUp() {
    paymentCommission1 = new PaymentCommission(1L,"Terry", 1000L, Date.from(Instant.now()),"1%");
    paymentCommission2 = new PaymentCommission(1L,"Terry", 11000L, Date.from(Instant.now()),"3%");
    paymentCommission3 = new PaymentCommission(1L,"Terry", 110000L, Date.from(Instant.now()),"5%");
    paymentCommissions.add(paymentCommission1);
    paymentCommissions.add(paymentCommission2);
    paymentCommissions.add(paymentCommission3);

    sumDTO = new SumDTO(12300L);
    sumDTOList.add(sumDTO);

    paymentDTO = new PaymentDTO("Vladik",5000L);
    paymentDTOList.add(paymentDTO);


    }

    @AfterEach
    void tearDown() {
        paymentCommission1 = null; //обнулем
        paymentCommission2 = null; //обнулем
        paymentCommission3 = null; //обнулем
        paymentCommissions.clear();

        sumDTO = null;
        sumDTOList.clear();

        paymentDTO = null;
        paymentDTOList.clear();
    }

    @Test
    void save() {
//        doNothing().doThrow(new RuntimeException()).when(paymentRepository).save(paymentCommission); // в рамках этого теста (ничего делать не буду)
        SumCommition sumCommition = paymentService.save(paymentCommission1);
        assertNotNull(sumCommition);
        assertEquals(paymentService.save(paymentCommission1).getSum(),paymentCommission1.getSumPayment());

        Double d = Double.valueOf(paymentCommission1.getSumPayment()/100)*1;
        Double x = paymentCommission1.getSumPayment() -d;
        assertEquals(x , sumCommition.getSumCommition());
//        paymentCommission.setSumPayment(11000L);
        Double d1 = Double.valueOf(paymentCommission2.getSumPayment()/100)*3;
        Double x1 = paymentCommission2.getSumPayment() -d1;
        assertEquals(x1 , paymentCommission2.getSumPayment() - (Double.valueOf(paymentCommission2.getSumPayment()/100)*3));

        Double d2 = Double.valueOf(paymentCommission3.getSumPayment()/100)*5;
        Double x2 = paymentCommission3.getSumPayment() -d2;
        assertEquals(x2 , paymentCommission3.getSumPayment() - (Double.valueOf(paymentCommission3.getSumPayment()/100)*5));
    }

    @Test
    void findAll() {
        when(paymentRepository.findAll()).thenReturn(paymentCommissions);
        List<PaymentCommission> newList = paymentService.findAll();
        assertEquals(paymentCommissions,null); // сравниваем 2-списка м/д собой
    }

    @Test
    void listDto() {
        when(paymentRepository.listDto()).thenReturn(paymentDTOList);
        List<PaymentDTO> newListPaymentDTO = paymentService.listDto();
        assertEquals(paymentDTOList, newListPaymentDTO);
    }

    @Test
    void listSumDTO() {
        when(paymentRepository.listSumDTO()).thenReturn(sumDTOList);
        List<SumDTO> newListSumDTO = paymentService.listSumDTO();
        assertEquals(sumDTOList, newListSumDTO);

    }

    @Test
    void findByDate() {
    }

    @Test
    void findByDateAfter() {
        Date newDate = Date.from(Instant.now());
        when(paymentRepository.findByDatePaymentAfter(newDate)).thenReturn(paymentCommissions);
        List<PaymentCommission> newListDate = paymentService.findByDateAfter(newDate);
        assertEquals(paymentCommissions, newListDate);


    }

    @Test
    void findByDateBefore() {
    }
}