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

    @NotNull(message = "Nombre no debe ser nulo")
    private String nombre;

    @NotNull(message = "Despcripcion no debe ser nula")
    private String descripcion;

    @NotNull(message = "Stock no debe ser nula")
    @Size(min=1, message = "Stock debe ser minimo 1")
    private Integer stock;

    private Integer usuarioId;
}
