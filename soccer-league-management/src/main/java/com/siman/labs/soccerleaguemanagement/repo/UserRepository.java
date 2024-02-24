package com.siman.labs.soccerleaguemanagement.repo;

import com.siman.labs.soccerleaguemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUserByUserName(String userName);
}
