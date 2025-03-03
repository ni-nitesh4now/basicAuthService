package com.baseauth.springjwt.entity;

import com.baseauth.springjwt.payload.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {

    @NotBlank
    @Size(max = 20)
    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "cargo_capacity", nullable = false)
    private BigDecimal cargoCapacity;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Vendor owner; // References Vendor entity if owner is a vendor

    @NotBlank
    @Size(max = 10)
    @Column(name = "owner_pan", nullable = false)
    private String ownerPan;

    @Size(max = 50)
    @Column(name = "vehicle_make")
    private String vehicleMake;

    @Size(max = 50)
    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "manufacturing_year")
    private Integer manufacturingYear;

    @Column(name = "fitness_certificate_url")
    private String fitnessCertificateUrl;

    @Column(name = "registration_certificate_url")
    private String registrationCertificateUrl;

    @Column(name = "insurance_url")
    private String insuranceUrl;

    @Column(name = "pollution_certificate_url")
    private String pollutionCertificateUrl;

    @Column(name = "last_service_date")
    private LocalDate lastServiceDate;

    @Column(name = "next_service_date")
    private LocalDate nextServiceDate;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private Boolean isActive = true;
}
