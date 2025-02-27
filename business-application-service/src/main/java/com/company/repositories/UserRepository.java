package com.company.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.model.User;

// This repository file is for User table CRUD
// JpaRepository<Table name(entity), Primary key type>
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByname(String name);

    Boolean existsByname(String name);
  
    Boolean existsByEmail(String email);
}
