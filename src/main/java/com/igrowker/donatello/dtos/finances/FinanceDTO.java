package com.igrowker.donatello.dtos.finances;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinanceDTO {
    /*
    id_finanza = models.AutoField(primary_key=True)
    tipo = models.CharField(max_length=100)
    monto = models.DecimalField(max_digits=10, decimal_places=2)
    fecha = models.DateField()
    id_usuario = models.ForeignKey(User, on_delete=models.CASCADE)     */
    private Integer id;
    private String type;
    private Double amount;
    private LocalDate date; // YYYY-MM-DD
    private Integer userID;
}
