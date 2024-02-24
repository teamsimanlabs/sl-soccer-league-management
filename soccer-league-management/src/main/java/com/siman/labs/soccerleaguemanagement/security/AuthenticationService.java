package com.siman.labs.soccerleaguemanagement.security;

import com.siman.labs.soccerleaguemanagement.model.JwtAuthenticationResponse;
import com.siman.labs.soccerleaguemanagement.model.SignUpRequest;
import com.siman.labs.soccerleaguemanagement.model.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
