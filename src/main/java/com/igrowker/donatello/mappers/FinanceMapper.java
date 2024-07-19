package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.finances.FinanceDTO;
import com.igrowker.donatello.dtos.finances.FinanceExternalDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinanceMapper {
    public FinanceDTO toFinanceDto(FinanceExternalDto dto){
        return FinanceDTO.builder()
                .id(dto.getId_finanza())
                .type(dto.getTipo())
                .amount(dto.getMonto())
                .date(dto.getFecha())
                .userID(dto.getId_usuario())
                .build();
    }
    public List<FinanceDTO> toFinanceDtoList(List<FinanceExternalDto> dtoList){
        return dtoList.stream().map(dto-> this.toFinanceDto(dto)).collect(Collectors.toList());
    }
}
