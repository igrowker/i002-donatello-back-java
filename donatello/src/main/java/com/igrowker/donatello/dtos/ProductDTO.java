package com.igrowker.donatello.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Integer id; // todo AQUI AGREGUE EL ID DE PRODUCTO, ME PARECE IMPORTANTE RETORNARLO PARA CUESTIONES DE FRONT EN FUTURAS CONSULTAS, YA QUE SI SE EDITA O ELIMINA UN PRODUCTO ES REQUERIDO EL ID

    private String name;

    private String description;

    private Integer stock;

    private Integer userID;
}

