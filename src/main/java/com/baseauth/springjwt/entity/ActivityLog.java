package com.baseauth.springjwt.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "activity_logs")
public class ActivityLog {

    @Id
    private String id;
    private Date timestamp;
    private String activityType;
    private String entityId;
    private String entityType;
    private String userId;
    private String userType;
    private Map<String, Object> details;  // Flexible structure
    private boolean notificationSent;
    private NotificationDetails notificationDetails;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NotificationDetails {
        private List<String> sentTo;
        private Date sentAt;
        private String messageTemplate;
        private Map<String, Object> messageParams;
    }
}
