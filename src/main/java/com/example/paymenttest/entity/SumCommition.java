package com.example.paymenttest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//выходной класс
public class SumCommition {
    private Double sumCommition;
    private Long sum;
    private Long commition;
}
