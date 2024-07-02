package com.igrowker.donatello.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequest {
    @NotNull(message = "Nombre no puede ser nulo")
    @Size(min=2, max=30, message = "Nombre debe tener entre 2 y 30 caracteres")
    String nombre;

    @NotNull(message = "Email no puede ser nulo")
    @Email(message = "Email con formato invalido")
    String email;

    @NotNull(message = "Contraseña no puede ser nula")
    @Size(min=2, max=30, message = "Contraseña debe tener entre 2 y 30 caracteres")
    String contrasena;

    @NotNull(message = "Confirmacion de contraseña no puede ser nula")
    @Size(min=2, max=30, message = "Confirmacion de contraseña debe tener entre 2 y 30 caracteres")
    String contrasena2;

    @NotNull(message = "Telefono no puede ser nulo")
    @Size(min=7, max=14, message = "Telefono debe tener entre 7 y 14 caracteres")
    String telefono;

}