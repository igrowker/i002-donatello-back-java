package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.profile.ProfileAddDto;
import com.igrowker.donatello.dtos.profile.ProfileReadDto;
import com.igrowker.donatello.models.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    public ProfileReadDto toReadDto(Profile profile){

        return new ProfileReadDto().builder()
                .name(profile.getUser().getName())
                .mail(profile.getUser().getMail())
                .phone(profile.getUser().getPhone())
                // TODO => DEBERIA SER ENVIADA PARA CAMBIARSE POR EL USUARIO? .contrasena(profile.getUsuario().getContrasena())
                .companyName(profile.getCompanyName())
                .address(profile.getAddress())
                .addressDetails(profile.getAddressDetails())
                .city(profile.getCity())
                .postalCode(profile.getPostalCode())
                .country(profile.getCountry())
                .build();
    }
    public Profile toEntity(ProfileAddDto addDto){
        return new Profile().builder()
                .user(addDto.getUser())
                .companyName(addDto.getCompanyName())
                .address(addDto.getAddress())
                .addressDetails(addDto.getAddressDetails())
                .city(addDto.getCity())
                .postalCode(addDto.getPostalCode())
                .country(addDto.getCountry())
                .build();
    }


}
