package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.MenuProductDto;
import com.igrowker.donatello.models.MenuProduct;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MenuProductMapper {

    @Mappings({
            @Mapping(target = "id.idMenu", ignore = true),
            @Mapping(target = "id.idProduct", source = "productId")
    })
    MenuProduct toMenusProduct(MenuProductDto menuProductDto);

    @Mapping(target = "productId", source = "id.idProduct")
    MenuProductDto toMenuProductDto(MenuProduct menuProduct);
}
