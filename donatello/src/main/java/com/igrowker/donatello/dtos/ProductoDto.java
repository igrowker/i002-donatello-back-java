package com.igrowker.donatello.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDto {

    private String nombre;

    private String descripcion;

    private Integer stock;

    private Integer usuarioId;
}
