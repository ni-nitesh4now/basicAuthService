package com.baseauth.springjwt.entity;

import com.baseauth.springjwt.payload.enums.QuoteStatus;
import com.baseauth.springjwt.payload.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
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
@Table(name = "vendor_quotes")
public class VendorQuote extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "cargo_capacity", nullable = false)
    private BigDecimal cargoCapacity; // Capacity in tons

    @NotNull
    @Digits(integer = 12, fraction = 2)
    @Column(name = "quoted_amount", nullable = false)
    private BigDecimal quotedAmount;

    @Column(name = "is_selected", nullable = false)
    @ColumnDefault("false")
    private Boolean isSelected = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "quote_status", nullable = false)
    @ColumnDefault("'PENDING'")
    private QuoteStatus quoteStatus = QuoteStatus.PENDING;

    @NotNull
    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil;

    /**
     * Checks if the quote is still valid based on the expiration time.
     *
     * @return boolean - True if the quote is still valid.
     */
    public boolean isQuoteValid() {
        return LocalDateTime.now().isBefore(validUntil);
    }
}
