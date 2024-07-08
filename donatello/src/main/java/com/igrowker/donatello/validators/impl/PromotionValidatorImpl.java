package com.igrowker.donatello.validators.impl;

import com.igrowker.donatello.dtos.PromotionDto;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.validators.PromotionValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PromotionValidatorImpl implements PromotionValidator {
    @Override
    public void validate(PromotionDto promotionDto) {
        validateDescription(promotionDto.getDescription());
        validateStartDate(promotionDto.getStartDate());
        validateEndDate(promotionDto.getEndDate());
        // todo se obtiene y validad desde JWT en pasos posteriores => validateUserId(promotionDto.getUserId());

    }

    private void validateDescription(String description) {
        if (description == null || description.isEmpty()){
            throw new FieldInvalidException("The description field is required");
        }
    }

    private void validateStartDate(LocalDateTime startDate) {
        if (startDate == null ) {
            throw new FieldInvalidException("The startdate field is required");
        }
    }

    private void validateEndDate(LocalDateTime endDate) {
        if (endDate == null ) {
            throw new FieldInvalidException("The EndDate field is required");
        }
    }

    private void validateUserId(Integer userId) {
        if (userId == null) {
            throw new FieldInvalidException("The userId field is required");
        }
    }
}
