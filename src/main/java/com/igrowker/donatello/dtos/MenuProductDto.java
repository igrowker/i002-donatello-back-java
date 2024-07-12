package com.igrowker.donatello.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuProductDto {

    private Integer productId;

    private Integer amount;
}
