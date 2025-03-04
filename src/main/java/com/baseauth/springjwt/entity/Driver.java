package com.baseauth.springjwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "drivers")
public class Driver extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 15)
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank
    @Size(max = 20)
    @Column(name = "driving_license_number", unique = true, nullable = false)
    private String drivingLicenseNumber;

    @Column(name = "driving_license_url")
    private String drivingLicenseUrl;

    @NotNull
    @Column(name = "driving_license_expiry", nullable = false)
    private LocalDate drivingLicenseExpiry;

    @NotBlank
    @Size(max = 12)
    @Column(name = "aadhar_number", unique = true, nullable = false)
    private String aadharNumber;

    @Column(name = "aadhar_card_url")
    private String aadharCardUrl;

    @NotBlank
    @Size(max = 10)
    @Column(name = "pan_number", unique = true, nullable = false)
    private String panNumber;

    @Column(name = "pan_card_url")
    private String panCardUrl;

}
