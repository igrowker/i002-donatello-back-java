package com.igrowker.donatello.services;

import com.igrowker.donatello.auth.AuthResponse;
import com.igrowker.donatello.auth.JWTUtils;
import com.igrowker.donatello.auth.LoginRequest;
import com.igrowker.donatello.auth.RegisterRequest;
import com.igrowker.donatello.dtos.profile.ProfileUpdateDto;
import com.igrowker.donatello.models.CustomUser;
import com.igrowker.donatello.exceptions.BadCredentialsException;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

        public void validateNewMail(String mail){
            if(userRepository.existsByMail(mail))  throw new ConflictException("Email ya en uso!");
        }

        public AuthResponse register(RegisterRequest registerRequest) {
            if (! registerRequest.getPassword().equals(registerRequest.getPassword2())) throw new FieldInvalidException("Passwords no coinciden!");

            validateNewMail(registerRequest.getMail());
            CustomUser user = new CustomUser().builder()
                    .name(registerRequest.getName())
                    .mail(registerRequest.getMail())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .phone(registerRequest.getPhone())
                    .build();
            userRepository.save(user);
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getMail(),
                            registerRequest.getPassword()));
            return AuthResponse.builder()
                    .token(jwtUtils.generateToken(user))
                    .build();
        }

        public AuthResponse login(LoginRequest loginRequest) {
            UserDetails userDetails = userRepository
                    .findByMail(loginRequest.getMail())
                    .orElseThrow(()->new BadCredentialsException());

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMail(),
                            loginRequest.getPassword()));

            String token = jwtUtils.generateToken(userDetails);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        }

        public CustomUser getLoguedUser(HttpHeaders headers){
            SecurityContext securityContext = SecurityContextHolder.getContext();
            return (CustomUser) securityContext.getAuthentication().getPrincipal(); // retorna el usuario que envia el jwt
        }

        public CustomUser updateUser(CustomUser user, ProfileUpdateDto profileUpdateDto){
            if(profileUpdateDto.getName()!= null) user.setName(profileUpdateDto.getName());
            if(profileUpdateDto.getPhone()!= null) user.setPhone(profileUpdateDto.getPhone());
            if(profileUpdateDto.getPassword()!= null) user.setPassword(passwordEncoder.encode(profileUpdateDto.getPassword()));
            return userRepository.save(user);
        }
}
