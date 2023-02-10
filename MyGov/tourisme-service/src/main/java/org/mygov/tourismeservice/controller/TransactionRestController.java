package org.mygov.tourismeservice.controller;

import org.mygov.tourismeservice.model.TransactionRequestDto;
import org.mygov.tourismeservice.model.TransactionResponseDto;
import org.mygov.tourismeservice.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionRestController {

    private TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/transaction")
    TransactionResponseDto addTransaction(TransactionRequestDto transactionRequestDto){
        TransactionResponseDto transactionResponseDto = this.transactionService.addTransaction(transactionRequestDto);
        return transactionResponseDto;
    }



}
