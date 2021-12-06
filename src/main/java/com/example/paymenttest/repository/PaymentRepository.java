package com.example.paymenttest.repository;

import com.example.paymenttest.DTO.PaymentDTO;
import com.example.paymenttest.DTO.SumDTO;
import com.example.paymenttest.entity.PaymentCommission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentCommission, Long> {
    @Query(value = "select new com.example.paymenttest.DTO.PaymentDTO(client,sum(sumPayment)) from PaymentCommission group by client having sum(sumPayment)>=30000")
    public List<PaymentDTO> listDto();

    //Выборка по сумме
    @Query(value = "select new com.example.paymenttest.DTO.SumDTO(s.sumPayment) from PaymentCommission As s group by s.sumPayment")
    public List<SumDTO> listSumDTO();

    //Выборка по дате
    List<PaymentCommission> findByDatePayment(Date datePayment);

    List<PaymentCommission>findByDatePaymentAfter(Date datePayment);

    List<PaymentCommission>findByDatePaymentBefore(Date datePayment);
}
