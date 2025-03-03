package com.baseauth.springjwt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "customer_warehouses")
public class CustomerWarehouse extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotBlank
    @Size(max = 100)
    @Column(name = "warehouse_name", nullable = false)
    private String warehouseName;

    @NotBlank
    @Column(name = "address", columnDefinition = "TEXT", nullable = false)
    private String address;

    @NotBlank
    @Size(max = 50)
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank
    @Size(max = 50)
    @Column(name = "state", nullable = false)
    private String state;

    @NotBlank
    @Size(max = 10)
    @Column(name = "pincode", nullable = false)
    private String pincode;

    @Column(name = "geolocation", columnDefinition = "POINT")
    private String geolocation; //lat,long

    @Size(max = 100)
    @Column(name = "contact_person")
    private String contactPerson;

    @Size(max = 15)
    @Column(name = "contact_phone")
    private String contactPhone;

}
