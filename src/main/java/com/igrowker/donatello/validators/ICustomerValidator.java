package com.igrowker.donatello.validators;

import com.igrowker.donatello.dtos.CustomerDTO;

public interface ICustomerValidator {
    void validate(CustomerDTO target);
}
