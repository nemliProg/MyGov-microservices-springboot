package org.mygov.financeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @Builder @AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String ministry;
}
