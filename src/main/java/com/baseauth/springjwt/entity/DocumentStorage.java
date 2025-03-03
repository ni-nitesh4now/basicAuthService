package com.baseauth.springjwt.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "document_storage")
public class DocumentStorage {

    @Id
    private String id;
    private String documentType;
    private String entityId;
    private String entityType;
    private String s3Key;
    private String s3Url;
    private String fileName;
    private long fileSize;
    private String mimeType;
    private Date uploadedAt;
    private Date expiryDate;
    private String verificationStatus;
    private String verifiedBy;
    private Date verifiedAt;
    private Map<String, Object> metadata;
}
