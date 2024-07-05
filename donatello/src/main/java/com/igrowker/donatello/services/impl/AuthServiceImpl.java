package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.AuthDTO;
import com.igrowker.donatello.utils.JWTUtils;
import com.igrowker.donatello.dtos.LoginDTO;
import com.igrowker.donatello.dtos.RegisterDTO;
import com.igrowker.donatello.models.CustomUser;
import com.igrowker.donatello.exceptions.BadCredentialsException;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.FieldInvalidException;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    public void validateNewEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new ConflictException("This Email is already registered!");
    }

    public AuthDTO register(RegisterDTO registerDTO) {
        if (!registerDTO.getContrasena().equals(registerDTO.getContrasena2()))
            throw new FieldInvalidException("Passwords do not match!");

        validateNewEmail(registerDTO.getEmail());
        CustomUser user = new CustomUser().builder()
                .nombre(registerDTO.getNombre())
                .email(registerDTO.getEmail())
                .contrasena(passwordEncoder.encode(registerDTO.getContrasena()))
                .telefono(registerDTO.getTelefono())
                .build();
        userRepository.save(user);
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(registerDTO.getEmail(),
                        registerDTO.getContrasena()));
        return AuthDTO.builder()
                .token(jwtUtils.generateToken(user))
                .build();
    }

    public AuthDTO login(LoginDTO loginDTO) {
        UserDetails userDetails = userRepository
                .findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new BadCredentialsException());

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                        loginDTO.getContrasena()));

        String token = jwtUtils.generateToken(userDetails);
        return AuthDTO.builder()
                .token(token)
                .build();
    }
}
