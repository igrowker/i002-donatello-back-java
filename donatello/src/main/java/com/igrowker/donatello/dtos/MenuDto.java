package com.igrowker.donatello.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {

    private String name;

    private String description;

    private Integer userId;

    private List<MenuProductDto> menuProductDto;
}
