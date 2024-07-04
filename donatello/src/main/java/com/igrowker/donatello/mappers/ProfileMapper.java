package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.profile.ProfileAddDto;
import com.igrowker.donatello.dtos.profile.ProfileReadDto;
import com.igrowker.donatello.models.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    // String nombre; // en usuario
    // String email; // en usuario
    // String telefono; // en usuario
    // String contrasena; // en usuario

    // String nombreEmpresa;
    // String direccion;
    // String direccionExtra;
    // String ciudad;
    // String codigoPostal;
    // String pais;

    public ProfileReadDto toReadDto(Profile profile){

        return new ProfileReadDto().builder()
                .nombre(profile.getUsuario().getNombre())
                .email(profile.getUsuario().getEmail())
                .telefono(profile.getUsuario().getTelefono())
                // TODO => DEBERIA SER ENVIADA PARA CAMBIARSE POR EL USUARIO? .contrasena(profile.getUsuario().getContrasena())
                .nombreEmpresa(profile.getNombreEmpresa())
                .direccion(profile.getDireccion())
                .direccionExtra(profile.getDireccionExtra())
                .ciudad(profile.getCiudad())
                .codigoPostal(profile.getCodigoPostal())
                .pais(profile.getPais())
                .build();
    }
    public Profile toEntity(ProfileAddDto addDto){
        return new Profile().builder()
                .usuario(addDto.getUsuario())
                .nombreEmpresa(addDto.getNombreEmpresa())
                .direccion(addDto.getDireccion())
                .direccionExtra(addDto.getDireccionExtra())
                .ciudad(addDto.getCiudad())
                .codigoPostal(addDto.getCodigoPostal())
                .pais(addDto.getPais())
                .build();
    }


}
