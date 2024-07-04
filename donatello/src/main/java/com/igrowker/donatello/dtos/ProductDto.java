package com.igrowker.donatello.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String name;

    private String description;

    private Integer stock;

    private Integer userId;
}
