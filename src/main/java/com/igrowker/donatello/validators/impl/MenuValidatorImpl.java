package com.igrowker.donatello.validators.impl;

import com.igrowker.donatello.dtos.MenuDto;
import com.igrowker.donatello.dtos.MenuProductDto;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.validators.IMenuValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuValidatorImpl implements IMenuValidator {
    @Override
    public void validate(MenuDto menuDto) {
        nameValidator(menuDto.getName());
        descriptionValidator(menuDto.getDescription());
        menuProductValidator(menuDto.getMenuProductDto());
    }

    private void nameValidator(String name) {
        if (name == null || name.isEmpty()){
            throw new FieldInvalidException("The Name field must not be null");
        }
    }

    private void descriptionValidator(String description){
        if (description == null || description.isEmpty()){
            throw new FieldInvalidException("The Description field must not be null");
        }
    }

    private void menuProductValidator(List<MenuProductDto> menuProductDtoList) {
        if (menuProductDtoList == null || menuProductDtoList.isEmpty()) {
            throw new FieldInvalidException("The MenuProductDto list must not be null or empty");
        }

        menuProductDtoList.forEach(this::validateMenuProductDto);
    }

    private void validateMenuProductDto(MenuProductDto menuProductDto) {
        if (menuProductDto.getProductId() == null) {
            throw new FieldInvalidException("The productId field must not be null");
        }

        if (menuProductDto.getAmount() == null) {
            throw new FieldInvalidException("The amount field must not be null");
        }

    }


}
