package com.igrowker.donatello.validators.impl;

import com.igrowker.donatello.dtos.ProductoDto;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.validators.ProductValidator;
import org.springframework.stereotype.Service;

@Service
public class ProductValidatorImpl implements ProductValidator {
    @Override
    public void validate(ProductoDto productoDto) {
        nombreValidator(productoDto.getNombre());
        descripcionValidator(productoDto.getDescripcion());
        stockValidator(productoDto.getStock());
        usuarioIdValidatos(productoDto.getUsuarioId());
    }

    private void nombreValidator(String nombre) {
        if (nombre == null || nombre.isEmpty()){
            throw new FieldInvalidException("El campo nombre no debe ser null");
        }
    }

    private void descripcionValidator(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()){
            throw new FieldInvalidException("El campo descripcion no debe ser null");
        }
    }

    private void stockValidator(Integer stock) {
        if (stock == null){
            throw new FieldInvalidException("El campo stock no debe ser null");
        } else if (stock <= 0) {
            throw new FieldInvalidException("El campo stock debe ser minimo 1");
        }
    }

    private void usuarioIdValidatos(Integer usuarioId) {
        if (usuarioId == null){
            throw new FieldInvalidException("El campo usuarioId no debe ser null");
        }
    }
}
