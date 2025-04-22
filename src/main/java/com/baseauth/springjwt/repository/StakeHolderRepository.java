package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.Stakeholders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StakeHolderRepository extends JpaRepository<Stakeholders, Long> {
}
