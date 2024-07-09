package com.igrowker.donatello.controllers;

import com.igrowker.donatello.dtos.AuthDTO;
import com.igrowker.donatello.dtos.LoginDTO;
import com.igrowker.donatello.dtos.RegisterDTO;
import com.igrowker.donatello.services.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @GetMapping("protegido")
    public ResponseEntity<String> protegido (){
        return new ResponseEntity<>("hola soy un contenido.. protegido!", HttpStatus.OK);
    }

    @PostMapping("auth/login")
    public ResponseEntity<AuthDTO> login (@Valid @RequestBody LoginDTO loginDTO){
        return new ResponseEntity<>(authService.login(loginDTO), HttpStatus.OK);
    }

    @PostMapping("auth/register")
    public ResponseEntity<AuthDTO> register (@Valid @RequestBody RegisterDTO registerDTO){
        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }

}
