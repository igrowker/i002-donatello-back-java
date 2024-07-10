package com.igrowker.donatello.validators;

import com.igrowker.donatello.dtos.ProductDTO;

public interface IProductValidator {
    void validate(ProductDTO productDto);
}
