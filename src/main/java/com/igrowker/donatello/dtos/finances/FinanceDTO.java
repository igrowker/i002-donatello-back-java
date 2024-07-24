package com.igrowker.donatello.dtos.finances;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinanceDTO {

    private Integer id;
    private String type;
    private Double amount;
    private LocalDate date; // YYYY-MM-DD
    private Integer userID;
}
