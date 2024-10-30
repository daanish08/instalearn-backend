package com.ford.respository;

import com.ford.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {


    Admin findByAdminId(int adminId);
}

