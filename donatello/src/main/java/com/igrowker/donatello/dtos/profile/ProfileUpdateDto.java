package com.igrowker.donatello.dtos.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateDto {
    String nombre; // en usuario
    // NO SE DEBERIA PODER CAMBIAR ==> email;
    String telefono; // en usuario
    String contrasena; // en usuario

    String nombreEmpresa;
    String direccion;
    String direccionExtra;
    String ciudad;
    String codigoPostal;
    String pais;
}
