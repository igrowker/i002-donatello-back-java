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

    private String descripcion;

    private LocalDateTime fechaInicio;
    
    private LocalDateTime fechaFin;

    private Integer usuarioId;
}
