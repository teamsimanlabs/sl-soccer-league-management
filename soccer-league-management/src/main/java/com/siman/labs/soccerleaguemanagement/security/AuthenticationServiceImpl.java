package com.siman.labs.soccerleaguemanagement.security;

import com.siman.labs.soccerleaguemanagement.enums.Role;
import com.siman.labs.soccerleaguemanagement.model.JwtAuthenticationResponse;
import com.siman.labs.soccerleaguemanagement.model.SignUpRequest;
import com.siman.labs.soccerleaguemanagement.model.SigninRequest;
import com.siman.labs.soccerleaguemanagement.model.User;
import com.siman.labs.soccerleaguemanagement.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder()
                .accessToken(jwt)
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }


    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder()
                .accessToken(jwt)
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
}
