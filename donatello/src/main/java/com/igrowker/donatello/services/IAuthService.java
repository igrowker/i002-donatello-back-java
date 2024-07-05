package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.AuthDTO;
import com.igrowker.donatello.dtos.LoginDTO;
import com.igrowker.donatello.dtos.RegisterDTO;
import com.igrowker.donatello.dtos.profile.ProfileUpdateDto;
import com.igrowker.donatello.models.CustomUser;
import org.springframework.http.HttpHeaders;

public interface IAuthService {

    AuthDTO register(RegisterDTO registerDTO);

    AuthDTO login(LoginDTO loginDTO);

    CustomUser getLoguedUser(HttpHeaders headers);

    CustomUser updateUser(CustomUser user, ProfileUpdateDto profileUpdateDto);
}
