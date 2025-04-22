package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.OrderFulfillment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFulfillmentRepository extends JpaRepository<OrderFulfillment, Long> {
}
