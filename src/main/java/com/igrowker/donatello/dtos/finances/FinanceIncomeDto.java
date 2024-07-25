package com.igrowker.donatello.dtos.finances;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceIncomeDto {

    private Double incomeWeekly;
    private Double incomeMonthly;
}
