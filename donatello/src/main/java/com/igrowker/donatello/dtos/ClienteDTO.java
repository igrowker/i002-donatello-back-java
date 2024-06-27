package com.igrowker.donatello.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private String nombre;

    private String telefono;

    private String direccion;

    private Integer usuarioID;
}
