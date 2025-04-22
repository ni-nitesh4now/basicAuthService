package com.baseauth.springjwt.entity;

import com.baseauth.springjwt.payload.enums.StakeHolderType;
import com.baseauth.springjwt.payload.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "stakeholders")
public class Stakeholders extends BaseEntity {

    @NotBlank
    @Size(max = 50)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Size(max = 15)
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", columnDefinition = "TEXT")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private StakeHolderType stakeHolderType;

    @NotBlank
    @Column(name = "empId", nullable = false)
    private Long empId;
}
