package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.finances.FinanceDTO;
import com.igrowker.donatello.dtos.finances.FinanceExternalArrayDto;
import com.igrowker.donatello.dtos.finances.FinanceExternalDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FinanceMapper {
    public FinanceDTO toFinanceDto(FinanceExternalDto dto){
        return FinanceDTO.builder()
                .id(dto.getId())
                .type(dto.getType())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .userID(dto.getUserID())
                .build();
    }
    public List<FinanceDTO> toFinanceDtoList(FinanceExternalArrayDto dtoList){
        /*
        List<FinanceDTO> dtos = new ArrayList<>();
         for (int i = 0; i<dtoList.getDtoList().size(); i++){
             dtos.add(this.toFinanceDto(dtoList.getDtoList().get(i)));
         }
         return dtos;
         */
        return dtoList.getDtoList().stream().map(dto-> this.toFinanceDto(dto)).collect(Collectors.toList());
    }
}
