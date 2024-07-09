package com.igrowker.donatello.services;

import com.igrowker.donatello.auth.entities.CustomUser;
import com.igrowker.donatello.dtos.AuthDTO;
import com.igrowker.donatello.dtos.LoginDTO;
import com.igrowker.donatello.dtos.RegisterDTO;
import com.igrowker.donatello.exceptions.BadCredentialsException;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
        @Autowired
        private IUserRepository userRepository;
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        PasswordEncoder passwordEncoder;
        @Autowired
        private JWTUtils jwtUtils;

        public void validateNewEmail(String email){
            if(userRepository.existsByMail(email)) throw new ConflictException("Ya existe email registrado");
        }

        public AuthDTO register(RegisterDTO registerDto) {
            if (! registerDto.getPassword().equals(registerDto.getPassword2())) throw new FieldInvalidException("Passwords no coinciden");

            validateNewEmail(registerDto.getMail());
            CustomUser user = new CustomUser().builder()
                    .name(registerDto.getName())
                    .mail(registerDto.getMail())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .phone(registerDto.getPhone())
                    .build();
            userRepository.save(user);
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(registerDto.getMail(),
                            registerDto.getPassword()));
            return AuthDTO.builder()
                    .token(jwtUtils.generateToken(user))
                    .build();
        }

        public AuthDTO login(LoginDTO loginDto) {
            UserDetails userDetails = userRepository
                    .findByMail(loginDto.getMail())
                    .orElseThrow(()->new BadCredentialsException());

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getMail(),
                            loginDto.getPassword()));

            String token = jwtUtils.generateToken(userDetails);
            return AuthDTO.builder()
                    .token(token)
                    .build();
        }
}
