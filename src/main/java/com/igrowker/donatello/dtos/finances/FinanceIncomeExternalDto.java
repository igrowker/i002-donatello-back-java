package com.igrowker.donatello.dtos.finances;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceIncomeExternalDto {

    private Double total_ingresos_semana;
    private Double total_ingresos_mes;
}
