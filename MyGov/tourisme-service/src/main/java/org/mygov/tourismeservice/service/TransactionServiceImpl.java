package org.mygov.tourismeservice.service;

import org.mygov.tourismeservice.client.TransactionClient;
import org.mygov.tourismeservice.entities.Transactions;
import org.mygov.tourismeservice.mapper.TransactionMapper;
import org.mygov.tourismeservice.model.TransactionRequestDto;
import org.mygov.tourismeservice.model.TransactionResponseDto;
import org.mygov.tourismeservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private TransactionClient transactionClient;
    private TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionClient transactionClient, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionClient = transactionClient;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionResponseDto addTransaction(TransactionRequestDto transactionRequestDto){
        Transactions transactions = this.transactionMapper.TRANSACTION_REQUEST_DTO_TO_TRANSACTION(transactionRequestDto);

        // SEND THE REQUEST TO THE FINANCE SERVICE AND SAVE IT THERE
        this.transactionClient.addTransaction(transactionRequestDto);
        transactions.setDate(LocalDate.now());
        // SAVE THE TRANSACTION ON THIS SERVICE DATABASE
        Transactions save = this.transactionRepository.save(transactions);

        // MAP THE TRANSACTION ENTITY TO TRANSACTION RESPONSE
        TransactionResponseDto transactionResponseDto = this.transactionMapper.TRANSACTION_TO_TRANSACTION_RESPONSE_DTO(save);

        return transactionResponseDto;
    }


}
