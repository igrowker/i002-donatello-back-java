package com.igrowker.donatello.dtos.finances;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceExternalArrayDto {
    public List<FinanceExternalDto> dtoList;
}
