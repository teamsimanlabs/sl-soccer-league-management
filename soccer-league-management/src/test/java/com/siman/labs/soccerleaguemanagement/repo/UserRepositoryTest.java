package com.siman.labs.soccerleaguemanagement.repo;

import com.siman.labs.soccerleaguemanagement.enums.Role;
import com.siman.labs.soccerleaguemanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private  UserRepository repository;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Test
    void saveUserTest(){
        //arrang
        User user = getUser();
        //act
        User savedUser = repository.save(user);
        //assert

        assertEquals(1, savedUser.getId());
        assertEquals("patato@gmail.com", savedUser.getEmail());
        assertEquals(Role.USER, savedUser.getRole());
        boolean result = passwordEncoder.matches("password", savedUser.getPassword());
        assertTrue(result);


    }

    private User getUser() {
        User user = new User();
        user.setEmail("patato@gmail.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }



    @Test
    void findByEmail() {
    // arange
        User user = getUser();
        repository.save(user);

    // act
        Optional<User> byEmail = repository.findByEmail(user.getEmail());
    // Assert
        assertEquals(2, byEmail.get().getId());
        assertEquals("patato@gmail.com", byEmail.get().getEmail());
        assertEquals(Role.USER, byEmail.get().getRole());
        boolean result = passwordEncoder.matches("password", byEmail.get().getPassword());
        assertTrue(result);

        /*

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    }
         */
    }

    @Test
    void findUserByEmail() {
        // arrange
        User users = getUser();
        //act

        repository.save(users);
        User user = repository.findUserByEmail(users.getUsername());


    //assert

        assertEquals(3, user.getId());
        assertEquals("patato@gmail.com",user.getEmail());
        assertEquals(Role.USER,user.getRole());
        boolean result = passwordEncoder.matches("password", user.getPassword());
        assertTrue(result);




        /*
                return username -> {
            User userByEmail = userRepository.findUserByEmail(username);
            if (userByEmail != null) {
                return userByEmail;
            } else {
                throw new UsernameNotFoundException("User not found with email: " + username);
            }
         */
    }




}