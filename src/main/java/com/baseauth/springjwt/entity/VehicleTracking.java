package com.baseauth.springjwt.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="vehicle_tracking")
public class VehicleTracking {

    @Id
    private String id;
    private String vehicleId;
    private String orderId;
    private boolean active;
    private CurrentLocation currentLocation;
    private List<TrackingData> trackingData;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TrackingData {
        private Date timestamp;
        private double latitude;
        private double longitude;
        private double speed;
        private String status;
        private String address;
        private Double fuelLevel;
        private Double temperature;
        private String engineStatus;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CurrentLocation {
        private Date timestamp;
        private double latitude;
        private double longitude;
        private String address;
    }
}
