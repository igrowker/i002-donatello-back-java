package com.igrowker.donatello.dtos.profile;

import com.igrowker.donatello.models.CustomUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileAddDto {
    // No necesario para crear perfil, ya esta en usuario => String nombre;
    // No necesario para crear perfil, ya esta en usuario => String email;
    // No necesario para crear perfil, ya esta en usuario => String telefono;
    // No necesario para crear perfil, ya esta en usuario => String contrasena;
    CustomUser user;

    @NotNull(message = "Nombre de empresa no puede ser nulo.")
    @Size(min=2, max=30, message = "Nombre de empresa debe tener entre 2 y 30 caracteres")
    String companyName;

    @NotNull(message = "Direccion no puede ser nula.")
    @Size(min=2, max=30, message = "Direccion debe tener entre 2 y 30 caracteres")
    String address;

    String addressDetails;

    @NotNull(message = "Ciudad no puede ser nula.")
    @Size(min=2, max=30, message = "Ciudad debe tener entre 2 y 30 caracteres")
    String city;

    @NotNull(message = "Codigo postal no puede ser nulo.")
    @Size(min=2, max=30, message = "Codigo postal debe tener entre 2 y 30 caracteres")
    String postalCode;

    @NotNull(message = "Pais no puede ser nulo.")
    @Size(min=2, max=30, message = "Pais debe tener entre 2 y 30 caracteres")
    String country;
}
