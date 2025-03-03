package com.baseauth.springjwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "vendors")
public class Vendor extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @NotBlank
    @Size(max = 100)
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Size(max = 15)
    @Column(name = "gstin", unique = true)
    private String gstin;

    @NotBlank
    @Size(max = 10)
    @Column(name = "pan_number", unique = true, nullable = false)
    private String panNumber;

    @Size(max = 255)
    @Column(name = "msme_certificate_url")
    private String msmeCertificateUrl; // S3 URL

    @Size(max = 255)
    @Column(name = "pan_card_url")
    private String panCardUrl; // S3 URL

    @Size(max = 20)
    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Size(max = 15)
    @Column(name = "bank_ifsc")
    private String bankIfsc;

    @Size(max = 100)
    @Column(name = "bank_name")
    private String bankName;

    @Size(max = 100)
    @Column(name = "bank_account_holder")
    private String bankAccountHolder;

    @Column(name = "rating", precision = 3, scale = 2)
    @ColumnDefault("0.00")
    private Double rating = 0.00;

    @Column(name = "is_verified")
    @ColumnDefault("false")
    private Boolean isVerified = false;
}
