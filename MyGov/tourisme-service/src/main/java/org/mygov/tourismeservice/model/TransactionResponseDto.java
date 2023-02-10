package org.mygov.tourismeservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data @Builder
public class TransactionResponseDto {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String ministry;
}
