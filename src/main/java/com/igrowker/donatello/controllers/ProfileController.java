package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.CustomerDTO;
import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.dtos.profile.ProfileAddDto;
import com.igrowker.donatello.dtos.profile.ProfileReadDto;
import com.igrowker.donatello.dtos.profile.ProfileUpdateDto;
import com.igrowker.donatello.dtos.profile.PublicProfileReadDto;
import com.igrowker.donatello.exceptions.ErrorMessage;
import com.igrowker.donatello.services.impl.ProfileServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Productos")
@SecurityRequirement(name = "Bearer Authentication")
public class ProfileController {
    @Autowired
    ProfileServiceImpl profileService;

    @Operation(summary = "Retorna el perfil publico de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna perfil publico, o vacio si nunca fue creado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PublicProfileReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping("/{idUser}")
    public ResponseEntity<PublicProfileReadDto> getPublicProfile (@PathVariable Integer idUser){
        return new ResponseEntity<>(profileService.getPublicProfile(idUser), HttpStatus.OK);
    }

    @Operation(summary = "Retorna el perfil perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna perfil completo, o vacio si nunca fue creado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @GetMapping()
    public ResponseEntity<ProfileReadDto> getProfile (@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(profileService.getProfile(headers), HttpStatus.OK);
    }

    @Operation(summary = "Crea el perfil del usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna perfil completo creado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PostMapping()
    public ResponseEntity<ProfileReadDto> createProfile (@RequestHeader HttpHeaders headers, @Valid @RequestBody ProfileAddDto profileAddDto){
        return new ResponseEntity<>(profileService.createProfile(headers, profileAddDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Permite edicion del perfil perteneciente al usuario logueado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna perfil editado",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileReadDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Credenciales invalidas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) })
    })
    @PutMapping()
    public ResponseEntity<ProfileReadDto> editProfile (@RequestHeader HttpHeaders headers, @Valid @RequestBody ProfileUpdateDto profileUpdateDto){
        return new ResponseEntity<>(profileService.editProfile(headers, profileUpdateDto), HttpStatus.OK);
    }
}
