package com.igrowker.donatello.validators.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.igrowker.donatello.dtos.ClienteDTO;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.validators.IClienteValidator;
import org.springframework.stereotype.Component;

@Component
public class ClienteValidator implements IClienteValidator {

    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public void validar(ClienteDTO target) {
        ClienteDTO clienteDto = (ClienteDTO) target;
        validateNombre(clienteDto.getNombre());
        validateTelefono(clienteDto.getTelefono());
        validateDireccion(clienteDto.getDireccion());
    }

    private void validateNombre(String nombre){
        if (nombre == null || nombre.isEmpty()) {
            throw new FieldInvalidException("El campo Nombre es requerido");
        }
    }
    private void validateTelefono(String telefono){
        if (telefono == null || telefono.isEmpty()) {
            throw new FieldInvalidException("El campo Telefono es requerido");
        }

        try {
            PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(telefono, null);
            boolean isValid = phoneNumberUtil.isValidNumber(parsedPhoneNumber);

            if (!isValid) throw new FieldInvalidException("El formato del campo Telefono es invalido.");

        } catch (NumberParseException e) {
            throw new FieldInvalidException("El formato del campo Telefono es invalido.");
        }
    }
    private void validateDireccion(String direccion){
        if (direccion == null || direccion.isEmpty()) {
            throw new FieldInvalidException("El campo Direccion es requerido");
        }
    }

}
