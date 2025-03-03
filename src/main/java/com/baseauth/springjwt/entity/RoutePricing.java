package com.baseauth.springjwt.entity;

import com.baseauth.springjwt.payload.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "route_pricing", uniqueConstraints = {
        @UniqueConstraint(name = "route_vehicle_capacity", columnNames = {
                "source_city", "source_state",
                "destination_city", "destination_state",
                "vehicle_type", "cargo_capacity"
        })
})
public class RoutePricing extends BaseEntity {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "cargo_capacity", nullable = false)
    private BigDecimal cargoCapacity;

    @NotNull
    @Digits(integer = 12, fraction = 2)
    @Column(name = "base_market_price", nullable = false)
    private BigDecimal baseMarketPrice;

    @NotNull
    @Digits(integer = 12, fraction = 2)
    @Column(name = "quoted_price", nullable = false)
    private BigDecimal quotedPrice;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    @Column(name = "our_markup_percentage")
    @ColumnDefault("20.00")
    private BigDecimal ourMarkupPercentage = BigDecimal.valueOf(20.00);

}
