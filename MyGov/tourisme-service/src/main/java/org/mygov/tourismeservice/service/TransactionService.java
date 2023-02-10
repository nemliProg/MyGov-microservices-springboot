package org.mygov.tourismeservice.service;

import org.mygov.tourismeservice.model.TransactionRequestDto;
import org.mygov.tourismeservice.model.TransactionResponseDto;

public interface TransactionService {

    TransactionResponseDto addTransaction(TransactionRequestDto transactionRequestDto);
}
