package com.igrowker.donatello.validators.impl;

import com.igrowker.donatello.dtos.ProductDto;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.validators.ProductValidator;
import org.springframework.stereotype.Service;

@Service
public class ProductValidatorImpl implements ProductValidator {
    @Override
    public void validate(ProductDto productDto) {
        nameValidator(productDto.getName());
        descriptionValidator(productDto.getDescription());
        stockValidator(productDto.getStock());
        userIdValidator(productDto.getUserId());
    }

    private void nameValidator(String name) {
        if (name == null || name.isEmpty()){
            throw new FieldInvalidException("The name field must not be null");
        }
    }

    private void descriptionValidator(String description) {
        if (description == null || description.isEmpty()){
            throw new FieldInvalidException("The description field must not be null");
        }
    }

    private void stockValidator(Integer stock) {
        if (stock == null){
            throw new FieldInvalidException("The stock field must not be null");
        } else if (stock <= 0) {
            throw new FieldInvalidException("The stock field must be a minimum of 1");
        }
    }

    private void userIdValidator(Integer userId) {
        if (userId == null){
            throw new FieldInvalidException("The userId field must not be null");
        }
    }
}
