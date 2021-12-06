package com.example.paymenttest.controller;

import com.example.paymenttest.DTO.PaymentDTO;
import com.example.paymenttest.DTO.SumDTO;
import com.example.paymenttest.entity.PaymentCommission;
import com.example.paymenttest.entity.SumCommition;
import com.example.paymenttest.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    @PostMapping("/save")
    public ResponseEntity<SumCommition> newSave(@RequestBody PaymentCommission commission){
        return new ResponseEntity<>(paymentService.save(commission), HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public  ResponseEntity<List<PaymentCommission>> ListByAll(){
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/listDTO")
    public ResponseEntity<List<PaymentDTO>> newListDto(){
        return new ResponseEntity<>(paymentService.listDto(),HttpStatus.OK);
    }

    @GetMapping("/listSumDTO")
    public ResponseEntity<List<SumDTO>> newListSumDTO(){
        return new ResponseEntity<>(paymentService.listSumDTO(),HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<PaymentCommission>> findByDate(@DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam("date") Date datePayment){
        return new ResponseEntity<>(paymentService.findByDate(datePayment),HttpStatus.OK);
    }
    @GetMapping("/dateAfter")
    public ResponseEntity<List<PaymentCommission>> findByDateAfter(@DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam("date") Date datePayment){
        return new ResponseEntity<>(paymentService.findByDateAfter(datePayment), HttpStatus.OK);
    }
    @GetMapping("/dateBefore")
    public ResponseEntity<List<PaymentCommission>> findByDateBefore(@DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam("date") Date datePayment){
        return new ResponseEntity<>(paymentService.findByDateBefore(datePayment), HttpStatus.OK);
    }

//    @GetMapping("/topic/{message}")
//    public String sendMessage(@PathVariable String message){
//         kafKaProducerService.sendMessage(message);
//         return "Hello";
//    }



}
