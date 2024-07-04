package com.igrowker.donatello.dtos.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileReadDto {
    String nombre; // en usuario
    String email; // en usuario
    String telefono; // en usuario
    // TODO => DEBERIA SER ENVIADA PARA CAMBIARSE POR EL USUARIO? String contrasena; // en usuario
    String nombreEmpresa;
    String direccion;
    String direccionExtra;
    String ciudad;
    String codigoPostal;
    String pais;
}
