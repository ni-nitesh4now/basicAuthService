package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.ClientWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientWarehouseRepository extends JpaRepository<ClientWarehouse, Long> {
    List<ClientWarehouse>  findByClient_Id(Long clientId);

    ClientWarehouse findByClient_IdAndId(Long clientId, Long id);
}
