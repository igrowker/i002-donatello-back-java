package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.profile.ProfileAddDto;
import com.igrowker.donatello.dtos.profile.ProfileReadDto;
import com.igrowker.donatello.dtos.profile.ProfileUpdateDto;
import com.igrowker.donatello.dtos.profile.PublicProfileReadDto;
import com.igrowker.donatello.services.impl.ProfileServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
    @Autowired
    ProfileServiceImpl profileService;

    @GetMapping("/{idUser}")
    public ResponseEntity<PublicProfileReadDto> getPublicProfile (@PathVariable Integer idUser){
        return new ResponseEntity<>(profileService.getPublicProfile(idUser), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ProfileReadDto> getProfile (@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(profileService.getProfile(headers), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProfileReadDto> createProfile (@RequestHeader HttpHeaders headers, @Valid @RequestBody ProfileAddDto profileAddDto){
        return new ResponseEntity<>(profileService.createProfile(headers, profileAddDto), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<ProfileReadDto> editProfile (@RequestHeader HttpHeaders headers, @Valid @RequestBody ProfileUpdateDto profileUpdateDto){
        return new ResponseEntity<>(profileService.editProfile(headers, profileUpdateDto), HttpStatus.OK);
    }
}
