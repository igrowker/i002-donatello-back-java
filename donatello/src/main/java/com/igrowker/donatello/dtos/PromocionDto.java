package com.igrowker.donatello.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromocionDto {

    @NotNull(message = "Despcripcion no debe ser nula")
    private String descripcion;

    @NotNull(message = "Fecha inicio no debe ser nula")
    private LocalDateTime fechaInicio;

    @NotNull(message = "Fecha fin no debe ser nula")
    private LocalDateTime fechaFin;

    private Integer usuarioId;
}
