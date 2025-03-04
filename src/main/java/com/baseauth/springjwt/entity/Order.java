package com.baseauth.springjwt.entity;

import com.baseauth.springjwt.payload.enums.OrderStatus;
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
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "managed_by", unique = true)
    private Stakeholders managedBy;

    @NotBlank
    @Size(max = 20)
    @Column(name = "order_number", unique = true, nullable = false)
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "pickup_warehouse_id", nullable = false)
    private CustomerWarehouse pickupWarehouse;

    @ManyToOne
    @JoinColumn(name = "delivery_warehouse_id", nullable = false)
    private CustomerWarehouse deliveryWarehouse;

    @NotBlank
    @Size(max = 100)
    @Column(name = "cargo_type", nullable = false)
    private String cargoType;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "cargo_weight")
    private BigDecimal cargoWeight; // Cargo weight in tons

    @Digits(integer = 10, fraction = 2)
    @Column(name = "cargo_volume")
    private BigDecimal cargoVolume; // Cargo volume in cubic meters

    @Column(name = "required_vehicle_type", nullable = false)
    private String requiredVehicleType;

    @NotNull
    @Column(name = "pickup_date", nullable = false)
    private LocalDate pickupDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "special_instructions", columnDefinition = "TEXT")
    private String specialInstructions;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @ColumnDefault("'CREATED'")
    private OrderStatus status = OrderStatus.CREATED;

    @NotNull
    @Column(name = "contract_amount", nullable = false)
    private BigDecimal contractAmount;

}
