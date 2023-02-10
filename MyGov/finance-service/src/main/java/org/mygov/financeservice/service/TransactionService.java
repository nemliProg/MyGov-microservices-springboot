package org.mygov.financeservice.service;


import org.mygov.financeservice.model.TransactionRequestDto;
import org.mygov.financeservice.model.TransactionResponseDto;

public interface TransactionService {

    TransactionResponseDto addTransaction(TransactionRequestDto transactionRequestDto);
}
