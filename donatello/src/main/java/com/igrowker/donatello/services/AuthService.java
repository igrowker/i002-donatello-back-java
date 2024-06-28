package com.igrowker.donatello.services;

import com.igrowker.donatello.auth.AuthResponse;
import com.igrowker.donatello.auth.JWTUtils;
import com.igrowker.donatello.auth.LoginRequest;
import com.igrowker.donatello.auth.RegisterRequest;
import com.igrowker.donatello.auth.entities.CustomUser;
import com.igrowker.donatello.exceptions.AlreadyExistException;
import com.igrowker.donatello.exceptions.InvalidValueException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.UserMapper;
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
        @Autowired
        private UserMapper userMapper;

        public void validateNewEmail(String email){
            if(userRepository.existsByEmail(email)) throw new AlreadyExistException("Email ya en uso!");
        }

        public AuthResponse register(RegisterRequest registerRequest) {
            if (! registerRequest.getContrasena().equals(registerRequest.getContrasena2())) {
                throw new InvalidValueException("Passwords no coinciden!");
            }
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
                    .orElseThrow(()->new NotFoundException(("Usuario no encontrado!")));

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getContrasena()));

            String token = jwtUtils.generateToken(userDetails);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }
}
