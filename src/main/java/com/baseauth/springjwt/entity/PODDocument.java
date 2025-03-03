package com.baseauth.springjwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pod_documents")
public class PODDocument extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    private Order order;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "delivery_time")
    private Date deliveryTime;

    @Column(name = "delivery_location")
    private String deliveryLocation;

    @Column(name = "additional_comments")
    private String additionalComments;

    @Column(name = "penality")
    private Long penality;

    @Column(name = "document_url")
    private String documentUrl;

}
