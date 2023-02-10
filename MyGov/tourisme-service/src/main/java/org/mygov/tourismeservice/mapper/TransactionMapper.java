package org.mygov.tourismeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mygov.tourismeservice.entities.Transactions;
import org.mygov.tourismeservice.model.TransactionRequestDto;
import org.mygov.tourismeservice.model.TransactionResponseDto;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper MAPPER = Mappers.getMapper(TransactionMapper.class);

    Transactions TRANSACTION_REQUEST_DTO_TO_TRANSACTION(TransactionRequestDto transactionRequestDto);
    TransactionResponseDto TRANSACTION_TO_TRANSACTION_RESPONSE_DTO(Transactions transactions);

}
