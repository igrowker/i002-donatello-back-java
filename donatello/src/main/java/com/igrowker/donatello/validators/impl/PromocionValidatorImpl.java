package com.igrowker.donatello.validators.impl;

import com.igrowker.donatello.dtos.PromocionDto;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.validators.PromocionValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PromocionValidatorImpl implements PromocionValidator {
    @Override
    public void validar(PromocionDto promocionDto) {
        validateDescripcion(promocionDto.getDescripcion());
        validateFechaInicio(promocionDto.getFechaInicio());
        validateFechaFin(promocionDto.getFechaFin());
        validateUsuarioId(promocionDto.getUsuarioId());

    }

    private void validateDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()){
            throw new FieldInvalidException("El campo descripcion es requerido");
        }
    }

    private void validateFechaInicio(LocalDateTime fechaInicio) {
        if (fechaInicio == null ) {
            throw new FieldInvalidException("El campo fechaInicio es requerido");
        }
    }

    private void validateFechaFin(LocalDateTime fechaFin) {
        if (fechaFin == null ) {
            throw new FieldInvalidException("El campo fechaFin es requerido");
        }
    }

    private void validateUsuarioId(Integer usuarioId) {
        if (usuarioId == null) {
            throw new FieldInvalidException("El campo usuarioId es requerido");
        }
    }
}
