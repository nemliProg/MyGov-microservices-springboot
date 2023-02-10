package org.mygov.financeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mygov.financeservice.entities.Transaction;
import org.mygov.financeservice.model.TransactionRequestDto;
import org.mygov.financeservice.model.TransactionResponseDto;
import org.springframework.context.annotation.Profile;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper MAPPER = Mappers.getMapper(TransactionMapper.class);

    Transaction TRANSACTION_REQUEST_DTO_TO_TRANSACTION(TransactionRequestDto transactionRequestDto);
    TransactionResponseDto TRANSACTION_TO_TRANSACTION_RESPONSE_DTO(Transaction transactions);

}
