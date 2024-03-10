package com.baby.babycareproductsshop.entity.order;

import com.baby.babycareproductsshop.entity.CreatedAtEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_payment_option")
public class OrderPaymentOptionEntity extends CreatedAtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, columnDefinition = "BIGINT UNSIGNED")
    private Long ipaymentOption;

    @Column(length = 20, nullable = false)
    private String paymentOption;
}
