package com.baby.babycareproductsshop.entity.user;

import com.baby.babycareproductsshop.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "t_address")
public class UserAddressEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iaddress;

    @ManyToOne
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @NotNull
    @Column(length = 200)
    private String zipCode;

    @NotNull
    @Column(length = 50)
    private String address;

    @NotNull
    @Column(length = 50)
    private String addressDetail;
}
