package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.Credentials;
import com.baseauth.springjwt.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Object> findByCredentials(Credentials credentials);
}
