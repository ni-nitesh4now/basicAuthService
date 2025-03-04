package com.baseauth.springjwt.entity;

import com.baseauth.springjwt.payload.enums.PodStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "order_fulfillment")
public class OrderFulfillment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor; // Nullable, as the vehicle may be company-owned

    @ManyToOne
    @JoinColumn(name = "fulfilled_by")
    private Stakeholders fulfilledBy; // Added fulfilledBy

    @ManyToOne
    @JoinColumn(name = "loader_id")
    private Stakeholders loader;

    @ManyToOne
    @JoinColumn(name = "placement_person")
    private Stakeholders placementPerson;

    @ManyToOne
    @JoinColumn(name = "traffic")
    private Stakeholders trafficPerson; // Renamed for clarity

    @Digits(integer = 14, fraction = 2)
    @Column(name = "vendor_amount")
    private BigDecimal vendorAmount; // Payment to vendor

    @Column(name = "actual_pickup_time")
    private LocalDateTime actualPickupTime;

    @Column(name = "actual_delivery_time")
    private LocalDateTime actualDeliveryTime;

    @NotBlank
    @Size(max = 20)
    @Column(name = "bilty_number", unique = true, nullable = false)
    private String biltyNumber;

    @Column(name = "customer_bilty_url")
    private String customerBiltyUrl;

    @Column(name = "vendor_bilty_url")
    private String vendorBiltyUrl;

    @Column(name = "company_bilty_url")
    private String companyBiltyUrl;

    @Column(name = "pod_url")
    private String podUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "pod_status")
    @ColumnDefault("'PENDING'")
    private PodStatus podStatus = PodStatus.PENDING;

    @Column(name = "pod_remarks", columnDefinition = "TEXT")
    private String podRemarks;

    @Column(name = "is_penalized")
    @ColumnDefault("false")
    private Boolean isPenalized = false;

    @Digits(integer = 12, fraction = 2)
    @Column(name = "penalty_amount", nullable = false)
    @ColumnDefault("0.00")
    private BigDecimal penaltyAmount = BigDecimal.ZERO;

    @Column(name = "penalty_reason", columnDefinition = "TEXT")
    private String penaltyReason;
}
