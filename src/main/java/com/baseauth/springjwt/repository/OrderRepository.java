package com.baseauth.springjwt.repository;

import com.baseauth.springjwt.entity.Client;
import com.baseauth.springjwt.entity.Customer;
import com.baseauth.springjwt.entity.Order;
import com.baseauth.springjwt.entity.Stakeholders;
import com.baseauth.springjwt.payload.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_Client(Client client);

    List<Order> findByStatusAndCustomer_Client(OrderStatus orderStatus, Client client);

    @Query("SELECT o FROM Order o WHERE o.managedBy = :stakeHolder")
    List<Order> findByStakeHolder(@Param("stakeHolder") Stakeholders stakeHolder);

    List<Order> findByStatus(OrderStatus orderStatus);

    @Query("SELECT o FROM Order o WHERE o.customer = :customer")
    List<Order> findByCustomer(@Param("customer") Customer customer);

    Optional<Order> findByCustomer_ClientAndId(Client client, Long orderId);
}
