package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.profile.ProfileAddDto;
import com.igrowker.donatello.dtos.profile.ProfileReadDto;
import com.igrowker.donatello.dtos.profile.ProfileUpdateDto;
import com.igrowker.donatello.dtos.profile.PublicProfileReadDto;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.ProfileMapper;
import com.igrowker.donatello.models.CustomUser;
import com.igrowker.donatello.models.Profile;
import com.igrowker.donatello.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    ProfileMapper profileMapper;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    AuthService authService;

    public Profile getProfileByIdUser(Integer idUser){
        Optional<Profile> profile = profileRepository.findByIdUser(idUser);
        if(profile.isEmpty()) throw new NotFoundException("No existe el perfil del usuario");
        return profile.get();
    }
    public PublicProfileReadDto getPublicProfile(Integer idUser){
        Profile p = getProfileByIdUser(idUser);
        return PublicProfileReadDto.builder()
                .name(p.getUser().getName())
                .mail(p.getUser().getMail())
                .phone(p.getUser().getPhone())
                .companyName(p.getCompanyName())
                .address(p.getAddress())
                .addressDetails(p.getAddressDetails())
                .city(p.getCity())
                .postalCode(p.getPostalCode())
                .country(p.getCountry())
                .build();
    }
    public ProfileReadDto getProfile (HttpHeaders headers){
        CustomUser user = authService.getLoguedUser(headers);
        return profileMapper.toReadDto(getProfileByIdUser(user.getId()));
    }

    private boolean validateDataProfile(ProfileAddDto profileAddDto){
        // todo validaciones de Pais, ciudad, codigo postal => throw new FieldInvalidException("Valores invalidos...?");
        return true;
    }
    public ProfileReadDto createProfile (HttpHeaders headers, ProfileAddDto addDto){
        if(validateDataProfile(addDto)){
            // obtener usuario desde token, y agregarlo a profile, guardarlo y devolverlo como read
            addDto.setUser(authService.getLoguedUser(headers));

            System.out.println("USUARIO LOGUEADO ==================== ");
            System.out.println(authService.getLoguedUser(headers).getName());
            System.out.println(authService.getLoguedUser(headers).getMail());
            System.out.println("USUARIO LOGUEADO ==================== ");

            return profileMapper.toReadDto(profileRepository.save(profileMapper.toEntity(addDto)));
        }
        return null;
    }
    public ProfileReadDto editProfile (ProfileUpdateDto profileUpdateDto){

        return new ProfileReadDto();
    }


}
