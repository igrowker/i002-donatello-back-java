package com.igrowker.donatello.validators.impl;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.validators.IProductValidator;
import org.springframework.stereotype.Service;

@Service
public class ProductValidatorImpl implements IProductValidator {

    @Override
    public void validate(ProductDTO productDto) {
        nameValidator(productDto.getName());
        descriptionValidator(productDto.getDescription());
        stockValidator(productDto.getStock());
        // todo se obtiene desde JWT luego => validateUserID(customerDto.getUserID());
    }

    private void nameValidator(String name) {
        if (name == null || name.isEmpty()) {
            throw new FieldInvalidException("The Name field must not be null");
        }
    }

    private void descriptionValidator(String description) {
        if (description == null || description.isEmpty()) {
            throw new FieldInvalidException("The Description field must not be null");
        }
    }

    private void stockValidator(Integer stock) {
        if (stock == null) {
            throw new FieldInvalidException("The Stock field must not be null");
        } else if (stock <= 0) {
            throw new FieldInvalidException("The Stock field must be a minimum of 1");
        }
    }

    private void userIdValidator(Integer userId) {
        if (userId == null) {
            throw new FieldInvalidException("The UserId field must not be null");
        }
    }
}
