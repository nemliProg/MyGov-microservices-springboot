package org.mygov.tourismeservice.client;

import org.mygov.tourismeservice.entities.Transactions;
import org.mygov.tourismeservice.model.TransactionRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("finance-service")
public interface TransactionClient {

    @RequestMapping(method = RequestMethod.POST, value = "/transaction")
    Transactions addTransaction(TransactionRequestDto transactionRequestDto);

}
