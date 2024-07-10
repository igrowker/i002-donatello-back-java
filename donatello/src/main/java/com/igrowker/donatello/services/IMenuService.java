package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.MenuDto;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface IMenuService {

    MenuDto save(HttpHeaders headers, MenuDto menuDto);

    List<MenuDto> getMenus(HttpHeaders headers);

    MenuDto update(HttpHeaders headers,Integer id, MenuDto menuDto);

    MenuDto delete(HttpHeaders headers,Integer id);
}
