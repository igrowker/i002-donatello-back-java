package com.igrowker.donatello.dtos;

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
public class LoginDTO {
    @NotNull(message = "Email no puede ser nulo")
    @Size(min=2, max=30, message = "Email debe tener entre 2 y 30 caracteres")
    String mail;

    @NotNull(message = "Contraseña no puede ser nula")
    @Size(min=2, max=30, message = "Contraseña debe tener entre 2 y 30 caracteres")
    String password;
}
