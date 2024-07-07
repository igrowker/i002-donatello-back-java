package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.profile.ProfileAddDto;
import com.igrowker.donatello.dtos.profile.ProfileReadDto;
import com.igrowker.donatello.dtos.profile.ProfileUpdateDto;
import com.igrowker.donatello.dtos.profile.PublicProfileReadDto;
import com.igrowker.donatello.models.Profile;
import org.springframework.http.HttpHeaders;

public interface IProfileService {

    Profile getProfileByIdUser(Integer idUser);

    PublicProfileReadDto getPublicProfile(Integer idUser);

    ProfileReadDto getProfile(HttpHeaders headers);

    boolean validateDataProfile(ProfileAddDto profileAddDto);

    ProfileReadDto createProfile(HttpHeaders headers, ProfileAddDto addDto);
    ProfileReadDto editProfile(HttpHeaders headers, ProfileUpdateDto updateDto);
}
