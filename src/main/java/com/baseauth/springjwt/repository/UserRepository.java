package com.baseauth.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baseauth.springjwt.entity.Credentials;

@Repository
public interface UserRepository extends JpaRepository<Credentials, Long> {
  Optional<Credentials> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
