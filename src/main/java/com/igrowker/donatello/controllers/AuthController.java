package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.AuthDTO;
import com.igrowker.donatello.dtos.LoginDTO;
import com.igrowker.donatello.dtos.RegisterDTO;
import com.igrowker.donatello.exceptions.ErrorMessage;
import com.igrowker.donatello.services.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Authentication") // name of endpoint grup in swagger
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @Operation(summary = "Enviando username y password, retorna un JWT con las credenciales del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna un JWT",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad credentials",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }) })
    @PostMapping("login")
    public ResponseEntity<AuthDTO> login (@Valid @RequestBody LoginDTO loginDTO){
        return new ResponseEntity<>(authService.login(loginDTO), HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo usuario retorna un JWT con credenciales del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna un JWT",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad credentials",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }) })
    @PostMapping("register")
    public ResponseEntity<AuthDTO> register (@Valid @RequestBody RegisterDTO registerDTO){
        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }

}
