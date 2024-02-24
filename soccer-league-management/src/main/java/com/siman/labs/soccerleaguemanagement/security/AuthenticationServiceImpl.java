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

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .userName(request.getUserName()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        var user = userRepository.findUserByUserName(request.getUserName());
        if(null == user){
            throw new IllegalArgumentException("Invalid email or password");
        }
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}