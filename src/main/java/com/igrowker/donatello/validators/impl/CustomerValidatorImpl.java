package com.igrowker.donatello.validators.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.validators.ICustomerValidator;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidatorImpl implements ICustomerValidator {

    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public void validate(CustomerDTO target) {
        CustomerDTO customerDto = (CustomerDTO) target;
        validateName(customerDto.getName());
        validatePhone(customerDto.getPhone());
        validateAddress(customerDto.getAddress());
        // todo se obtiene desde JWT luego => validateUserID(customerDto.getUserID());
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new FieldInvalidException("The Name field is required");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new FieldInvalidException("The Phone field is required");
        }

        try {
            PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phone, null);
            boolean isValid = phoneNumberUtil.isValidNumber(parsedPhoneNumber);

            if (!isValid) throw new FieldInvalidException("The format of the Phone field is invalid.");

        } catch (NumberParseException e) {
            throw new FieldInvalidException("The format of the Phone field is invalid.");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new FieldInvalidException("The Address field is required");
        }
    }

    private void validateUserID(Integer userID) {
        if (userID == null) {
            throw new FieldInvalidException("The userID field is required");
        }
    }

}
