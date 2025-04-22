package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.ClientContracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ClientContractRepository extends JpaRepository<ClientContracts, Long> {
    Optional<ClientContracts> findByIdAndClientId(Long contractId, Long clientId);

    Optional<ClientContracts> findByClientId(Long clientId);

}
