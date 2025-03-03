package com.baseauth.springjwt.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="order_movement")
public class OrderMovement {

    @Id
    private String id;
    private String orderId;
    private String status;
    private List<StatusHistory> statusHistory;
    private OrderMetrics orderMetrics;
    private VehicleDetails vehicleDetails;
    private CustomerFeedback customerFeedback;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class StatusHistory {
        private String status;
        private Date timestamp;
        private GeoJsonPoint location;
        private String eventDetails;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class OrderMetrics {
        private Date pickupTime;
        private Date dropTime;
        private Date estimatedDeliveryTime;
        private Date actualDeliveryTime;
        private Double totalDistanceCovered;
        private Double totalTimeInTransit;
        private List<Delay> delays;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Delay {
        private String delayReason;
        private Double delayDuration;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class VehicleDetails {
        private String vehicleId;
        private String vehicleType;
        private String driverName;
        private String driverContactNumber;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CustomerFeedback {
        private Double rating;
        private String comments;
    }
}
