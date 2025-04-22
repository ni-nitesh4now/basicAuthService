package com.baseauth.springjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "client_contracts", uniqueConstraints = {
        @UniqueConstraint(name = "unique_client_route", columnNames = {"client", "route_pricing_id"})
})
public class ClientContracts extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_pricing_id", nullable = false)
    private RoutePricing routePricing;

    @Column(name = "contract_start_date", nullable = false)
    private LocalDate contractStartDate;

    @Column(name = "contract_end_date", nullable = false)
    private LocalDate contractEndDate;

    @Column(name = "credit_period_days", nullable = false)
    private Integer creditPeriodDays = 30; // Default will be set in Java, not DB-level

    @Column(name = "payment_terms", columnDefinition = "TEXT")
    private String paymentTerms;

    @Column(name = "agreement", columnDefinition = "TEXT")
    private String documentUrls;

}
