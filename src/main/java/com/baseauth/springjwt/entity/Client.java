package com.baseauth.springjwt.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    @NotBlank
    @Column(name = "clien_external_id", nullable = false)
    private String clientExternalId;

    @ManyToOne
    @JoinColumn(name = "managed_by")
    private Stakeholders managedBy;

    @Column(name = "company_name", unique = true)
    private String companyName;

    @Column(name = "registration_number", unique = true)
    private String registerationNumber;

    @Size(max = 15)
    @Column(name = "gstin", unique = true)
    private String gstin;

    @Size(max = 10)
    @Column(name = "pan_number", unique = true)
    private String panNumber;

    @ElementCollection
    @CollectionTable(name = "customer_document_urls", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "document_url")
    private List<String> documentUrls;
}
