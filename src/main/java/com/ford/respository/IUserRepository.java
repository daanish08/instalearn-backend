package com.ford.respository;

import com.ford.entity.Course;
import com.ford.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
