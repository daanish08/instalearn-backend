package com.ford.respository;

import com.ford.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUserId(int userId);
    User findByEmail(String email);

    @Query(value = "SELECT username FROM user WHERE userId = :userId", nativeQuery = true) // Replace "users" with your actual table name
    String findUsernameByUserId(@Param("userId") Integer userId);
    //    Optional<User> getUserByUserId(Integer id);

}
