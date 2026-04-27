package com.baloise.m295.library.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baloise.m295.library.common.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByName(String name);

}
