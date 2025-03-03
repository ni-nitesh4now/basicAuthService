package com.baseauth.springjwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "vendor_service_areas")
public class VendorServiceArea extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @NotBlank
    @Size(max = 50)
    @Column(name = "source_city", nullable = false)
    private String sourceCity;

    @NotBlank
    @Size(max = 50)
    @Column(name = "source_state", nullable = false)
    private String sourceState;

    @NotBlank
    @Size(max = 50)
    @Column(name = "destination_city", nullable = false)
    private String destinationCity;

    @NotBlank
    @Size(max = 50)
    @Column(name = "destination_state", nullable = false)
    private String destinationState;

    @NotBlank
    @Size(max = 50)
    @Column(name = "capacity", nullable = false)
    private String capacity;

}
