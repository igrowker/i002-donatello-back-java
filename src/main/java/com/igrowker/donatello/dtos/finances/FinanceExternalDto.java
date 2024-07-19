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
    /*
    id_finanza = models.AutoField(primary_key=True)
    tipo = models.CharField(max_length=100)
    monto = models.DecimalField(max_digits=10, decimal_places=2)
    fecha = models.DateField()
    id_usuario = models.ForeignKey(User, on_delete=models.CASCADE)     */

    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    // TODO ACA DEBERIA USAR @JsonProperty("tipo") => segun como hayan hecho en python
    private Integer id_finanza;
    private String tipo;
    private Double monto;
    private LocalDate fecha; // YYYY-MM-DD
    private Integer id_usuario;
}
