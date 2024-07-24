package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.finances.FinanceDTO;
import com.igrowker.donatello.dtos.finances.FinanceExternalDto;
import com.igrowker.donatello.dtos.finances.FinanceIncomeDto;
import com.igrowker.donatello.dtos.finances.FinanceIncomeExternalDto;
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
                .origin(dto.getMotivo())
                .description(dto.getDescripcion())
                .build();
    }

    public FinanceExternalDto toFinanceExternalDto(FinanceDTO dto){
        return FinanceExternalDto.builder()
                .id_finanza(dto.getId())
                .tipo(dto.getType())
                .monto(dto.getAmount())
                .fecha(dto.getDate())
                .id_usuario(dto.getUserID())
                .motivo(dto.getOrigin())
                .descripcion(dto.getDescription())
                .build();
    }
    public List<FinanceDTO> toFinanceDtoList(List<FinanceExternalDto> dtoList){
        return dtoList.stream().map(this::toFinanceDto).collect(Collectors.toList());
    }

    public FinanceExternalDto updateFinance(FinanceDTO financeDTO, FinanceExternalDto externalDto){
        if (externalDto == null){
            return null;
        }
        externalDto.setId_finanza(financeDTO.getId());
        externalDto.setTipo(financeDTO.getType());
        externalDto.setMonto(financeDTO.getAmount());
        externalDto.setFecha(financeDTO.getDate());
        externalDto.setDescripcion(financeDTO.getDescription());
        externalDto.setMotivo(financeDTO.getOrigin());
        //externalDto.setId_usuario(financeDTO.getUserID());
        return externalDto;
    }

    public FinanceIncomeDto toFinanceIncomeDto(FinanceIncomeExternalDto dto){
        return FinanceIncomeDto.builder()
                .incomeWeekly(dto.getTotal_ingresos_semana())
                .incomeMonthly(dto.getTotal_ingresos_mes())
                .build();
    }
}
