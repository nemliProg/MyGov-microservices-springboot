package org.mygov.financeservice.service;


import org.mygov.financeservice.entities.Transaction;
import org.mygov.financeservice.mapper.TransactionMapper;
import org.mygov.financeservice.model.TransactionRequestDto;
import org.mygov.financeservice.model.TransactionResponseDto;
import org.mygov.financeservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


    @Override
    public TransactionResponseDto addTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = this.transactionMapper.TRANSACTION_REQUEST_DTO_TO_TRANSACTION(transactionRequestDto);
        transaction.setDate(LocalDate.now());
        Transaction transaction1 = this.transactionRepository.save(transaction);

        TransactionResponseDto transactionResponseDto = this.transactionMapper.TRANSACTION_TO_TRANSACTION_RESPONSE_DTO(transaction1);
        return transactionResponseDto;
    }


}
