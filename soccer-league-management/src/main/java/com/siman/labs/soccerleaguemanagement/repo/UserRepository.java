package com.siman.labs.soccerleaguemanagement.repo;

import com.siman.labs.soccerleaguemanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findUserByEmail(String username);
}
