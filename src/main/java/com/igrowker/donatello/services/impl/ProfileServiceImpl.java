package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.auth.entities.CustomUser;
import com.igrowker.donatello.dtos.profile.ProfileAddDto;
import com.igrowker.donatello.dtos.profile.ProfileReadDto;
import com.igrowker.donatello.dtos.profile.ProfileUpdateDto;
import com.igrowker.donatello.dtos.profile.PublicProfileReadDto;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.ProfileMapper;
import com.igrowker.donatello.models.Profile;
import com.igrowker.donatello.repositories.IProfileRepository;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    ProfileMapper profileMapper;

    @Autowired
    IProfileRepository profileRepository;

    @Autowired
    IAuthService authService;

    public Profile getProfileByIdUser(Integer idUser) {
        Optional<Profile> profile = profileRepository.findByIdUser(idUser);
        if (profile.isEmpty()) throw new NotFoundException("User profile does not exist");
        return profile.get();
    }

    public PublicProfileReadDto getPublicProfile(Integer idUser) {
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

    public ProfileReadDto getProfile(HttpHeaders headers) {
        CustomUser user = authService.getLoguedUser(headers);
        return profileMapper.toReadDto(getProfileByIdUser(user.getId()));
    }

    public boolean validateDataProfile(ProfileAddDto profileAddDto) {
        // todo validaciones de Pais, ciudad, codigo postal => throw new FieldInvalidException("Valores invalidos...?");
        return true;
    }

    public ProfileReadDto createProfile(HttpHeaders headers, ProfileAddDto addDto) {
        if (validateDataProfile(addDto)) {
            // obtener usuario desde token, y agregarlo a profile, guardarlo y devolverlo como read
            addDto.setUser(authService.getLoguedUser(headers));
            return profileMapper.toReadDto(profileRepository.save(profileMapper.toEntity(addDto)));
        }
        return null;
    }

    public ProfileReadDto editProfile(HttpHeaders headers, ProfileUpdateDto updateDto) {
        CustomUser user = authService.getLoguedUser(headers);
        Profile profileToUpdate = getProfileByIdUser(user.getId());

        if (updateDto.getName() != null || updateDto.getPhone() != null || updateDto.getPassword() != null) {
            authService.updateUser(user, updateDto);
        }
        if (updateDto.getCompanyName() != null) profileToUpdate.setCompanyName(updateDto.getCompanyName());
        if (updateDto.getAddress() != null) profileToUpdate.setAddress(updateDto.getAddress());
        if (updateDto.getAddressDetails() != null) profileToUpdate.setAddressDetails(updateDto.getAddressDetails());
        if (updateDto.getCity() != null) profileToUpdate.setCity(updateDto.getCity());
        if (updateDto.getPostalCode() != null) profileToUpdate.setPostalCode(updateDto.getPostalCode());
        if (updateDto.getCountry() != null) profileToUpdate.setCountry(updateDto.getCountry());

        return profileMapper.toReadDto(profileRepository.save(profileToUpdate));
    }




}
