package com.igrowker.donatello.dtos.finances;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinanceExternalDto {

    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python

    private Integer id_finanza;

    private String tipo;

    private Double monto;

    private LocalDate fecha;

    private Integer id_usuario;
}
