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
    String name; // en usuario
    String mail; // en usuario
    String phone; // en usuario
    // TODO => DEBERIA SER ENVIADA PARA CAMBIARSE POR EL USUARIO? String contrasena; // en usuario
    String companyName;
    String address;
    String addressDetails;
    String city;
    String postalCode;
    String country;
}
