package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.MenuDto;
import com.igrowker.donatello.models.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MenuProductMapper.class})
public interface MenuMapper {

    @Mappings({
            @Mapping(target = "idUser", source = "userId"),
            @Mapping(target = "menuProducts", source = "menuProductDto")
    })
    Menu toMenu(MenuDto menuDto);

    @Mappings({
            @Mapping(target = "userId", source = "idUser"),
            @Mapping(target = "menuProductDto", source = "menuProducts")
    })
    MenuDto toMenuDto(Menu menu);

    List<MenuDto> toMenusDto(List<Menu> menus);

    //@Mapping(source = "userId", target = "idUser")
    void updateMenu(@MappingTarget Menu menu, MenuDto menuDto);
}
