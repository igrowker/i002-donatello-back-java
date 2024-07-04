package com.igrowker.donatello.dtos.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicProfileReadDto {
    String nombre;
    String email;
    String contrasena;
    String telefono;
    String nombreEmpresa;
    String direccion;
    String direccionExtra;
    String ciudad;
    String codigoPostal;
    String pais;
}

