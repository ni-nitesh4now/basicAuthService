package com.baseauth.springjwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "vehicle_types")
public class VehicleType extends BaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "length", nullable = false)
    private Long length;

    @NotBlank
    @Column(name = "breth", nullable = false)
    private Long breth;

    @NotBlank
    @Column(name = "height", nullable = false)
    private Long height;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "cargo_weight")
    private BigDecimal cargoWeight;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "cargo_volume")
    private BigDecimal cargoVolume;

}
