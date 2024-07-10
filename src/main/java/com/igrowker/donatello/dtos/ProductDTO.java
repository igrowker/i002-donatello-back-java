package com.igrowker.donatello.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Integer id;

    private String name;

    private String description;

    private Integer stock;

    private Integer userID;
}

