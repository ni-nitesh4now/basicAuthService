package com.baseauth.springjwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @NotBlank
    @Size(max = 100)
    @Column(name = "clien_external_id", nullable = false)
    private String clientExternalId;

    @Size(max = 15)
    @Column(name = "gstin", unique = true)
    private String gstin;

    @Size(max = 10)
    @Column(name = "pan_number", unique = true)
    private String panNumber;

    @Column(name = "contract_start_date", nullable = false)
    private LocalDate contractStartDate;

    @Column(name = "contract_end_date", nullable = false)
    private LocalDate contractEndDate;

    @Column(name = "credit_period_days", nullable = false, columnDefinition = "INT DEFAULT 30")
    private Integer creditPeriodDays = 30;

    @Column(name = "payment_terms", columnDefinition = "TEXT")
    private String paymentTerms;

    @ElementCollection
    @CollectionTable(name = "customer_document_urls", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "document_url")
    private List<String> documentUrls;
}
