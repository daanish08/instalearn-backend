package com.ford.respository;

import com.ford.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUserId(int userId);
    User findByEmail(String email);

    //    Optional<User> getUserByUserId(Integer id);

}
