package com.igrowker.donatello.controllers.auth;

import com.igrowker.donatello.auth.AuthResponse;
import com.igrowker.donatello.auth.LoginRequest;
import com.igrowker.donatello.auth.RegisterRequest;
import com.igrowker.donatello.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("protegido")
    public ResponseEntity<String> protegido (){
        return new ResponseEntity<>("hola soy un contenido.. protegido!", HttpStatus.OK);
    }

    @PostMapping("auth/login")
    public ResponseEntity<AuthResponse> login (@Valid @RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("auth/register")
    public ResponseEntity<AuthResponse> register (@Valid @RequestBody RegisterRequest registerRequest){
        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.CREATED);
    }

}
