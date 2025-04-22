package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.Credentials;
import com.baseauth.springjwt.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByCredentials(Credentials credentials);
}
