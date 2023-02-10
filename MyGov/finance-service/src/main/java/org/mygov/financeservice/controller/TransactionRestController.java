package org.mygov.financeservice.controller;

import org.mygov.financeservice.model.TransactionRequestDto;
import org.mygov.financeservice.model.TransactionResponseDto;
import org.mygov.financeservice.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionRestController {

    private TransactionService transactionService;

    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    TransactionResponseDto addTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
        TransactionResponseDto transactionResponseDto = this.transactionService.addTransaction(transactionRequestDto);
        return transactionResponseDto;
    }

}
