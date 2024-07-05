package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.MenuDto;
import com.igrowker.donatello.models.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MenuProductMapper.class})
public interface MenuMapper {

    @Mapping(target = "idUser", source = "userId")
    Menu toMenu(MenuDto menuDto);

    @Mapping(target = "userId", source = "idUser")
    MenuDto toMenuDto(Menu menu);

    List<MenuDto> toMenusDto(List<Menu> menus);

    @Mapping(source = "userId", target = "idUser")
    void updateMenu(@MappingTarget Menu menu, MenuDto menuDto);
}
