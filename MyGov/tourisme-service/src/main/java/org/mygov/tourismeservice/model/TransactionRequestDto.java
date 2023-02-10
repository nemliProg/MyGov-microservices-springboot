package org.mygov.tourismeservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data @Builder
public class TransactionRequestDto {
    private Double amount;
    private String ministry;
}
