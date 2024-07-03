package com.igrowker.donatello.services;

import com.igrowker.donatello.auth.AuthResponse;
import com.igrowker.donatello.auth.JWTUtils;
import com.igrowker.donatello.auth.LoginRequest;
import com.igrowker.donatello.auth.RegisterRequest;
import com.igrowker.donatello.models.CustomUser;
import com.igrowker.donatello.exceptions.BadCredentialsException;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        PasswordEncoder passwordEncoder;
        @Autowired
        private JWTUtils jwtUtils;

        public void validateNewEmail(String email){
            if(userRepository.existsByEmail(email))  throw new ConflictException("Email ya en uso!");
        }

        public AuthResponse register(RegisterRequest registerRequest) {
            if (! registerRequest.getContrasena().equals(registerRequest.getContrasena2())) throw new FieldInvalidException("Passwords no coinciden!");

            validateNewEmail(registerRequest.getEmail());
            CustomUser user = new CustomUser().builder()
                    .nombre(registerRequest.getNombre())
                    .email(registerRequest.getEmail())
                    .contrasena(passwordEncoder.encode(registerRequest.getContrasena()))
                    .telefono(registerRequest.getTelefono())
                    .build();
            userRepository.save(user);
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getEmail(),
                            registerRequest.getContrasena()));
            return AuthResponse.builder()
                    .token(jwtUtils.generateToken(user))
                    .build();
        }

        public AuthResponse login(LoginRequest loginRequest) {
            UserDetails userDetails = userRepository
                    .findByEmail(loginRequest.getEmail())
                    .orElseThrow(()->new BadCredentialsException());

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getContrasena()));

            String token = jwtUtils.generateToken(userDetails);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }
}
