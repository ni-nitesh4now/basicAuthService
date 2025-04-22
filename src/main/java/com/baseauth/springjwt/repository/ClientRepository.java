package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
