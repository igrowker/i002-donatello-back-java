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

    private Long providerId; // lo dejo solo para frontend

    // private Integer userID;
}

