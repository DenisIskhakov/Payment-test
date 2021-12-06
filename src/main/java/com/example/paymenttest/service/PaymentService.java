package com.example.paymenttest.service;

import com.example.paymenttest.DTO.PaymentDTO;
import com.example.paymenttest.DTO.SumDTO;
import com.example.paymenttest.entity.PaymentCommission;
import com.example.paymenttest.entity.SumCommition;
import com.example.paymenttest.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public SumCommition save(PaymentCommission commission){
        Long sum = commission.getSumPayment();
        Double d = 0.0 ;
        Double x = 0.0;
        if(sum > 0 && sum < 10000){
            d=Double.valueOf(sum/100)*1; // 1%
            x=sum -d; // сумма - % = выдадут конечную сумму
        }else{
            if(sum > 10000 && sum < 100000){
                d=Double.valueOf((sum/100)*3); // 3%
                x= sum -d;
            }else{
                if(sum > 100000){
                    d=Double.valueOf((sum/100)*5); // 5%
                    x= sum -d;
                }
            }
        }
        commission.setDatePayment(Date.from(Instant.now()));
        paymentRepository.save(commission);
        Long commision = Long.valueOf((long) (commission.getSumPayment() - x));
        return new SumCommition(x, commission.getSumPayment(), commision);
    }
    public List<PaymentCommission> findAll(){
        return paymentRepository.findAll();
    }
    public List<PaymentDTO> listDto(){
        return paymentRepository.listDto();
    }
    public List<SumDTO> listSumDTO(){
        return paymentRepository.listSumDTO();
    }
    public List<PaymentCommission> findByDate(Date datePayment){
        return paymentRepository.findByDatePayment(datePayment);
    }
    @GetMapping("/dateAfter")
    public List<PaymentCommission> findByDateAfter(Date datePayment){
        return paymentRepository.findByDatePaymentAfter(datePayment);
    }
    public List<PaymentCommission> findByDateBefore(Date datePayment){
        return paymentRepository.findByDatePaymentBefore(datePayment);
    }
}
