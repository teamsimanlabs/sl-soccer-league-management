package com.siman.labs.soccerleaguemanagement.service;

import com.siman.labs.soccerleaguemanagement.entity.User;
import com.siman.labs.soccerleaguemanagement.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            User userByEmail = userRepository.findUserByEmail(username);
            if (userByEmail != null) {
                return userByEmail;
            } else {
                throw new UsernameNotFoundException("User not found with email: " + username);
            }
        };
    }
}
