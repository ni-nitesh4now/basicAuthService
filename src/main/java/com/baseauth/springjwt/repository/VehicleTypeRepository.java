package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
