package com.baby.babycareproductsshop.entity.order;

import com.baby.babycareproductsshop.entity.BaseEntity;
import com.baby.babycareproductsshop.entity.user.UserAddressEntity;
import com.baby.babycareproductsshop.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_order")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iorder;

    @ManyToOne
    @JoinColumn(name = "iuser")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER) // lazy 에러 발생으로 eager 타입으로 변경 2024-02-26
    @JoinColumn(name = "iaddress")
    private UserAddressEntity userAddressEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ipayment_option", nullable = false)
    @ColumnDefault("'1'")
    private OrderPaymentOptionEntity orderPaymentOptionEntity;

    @Column(length = 20, name = "addressee_nm")
    private String addressNm;

    @Column(length = 13)
    private String phoneNumber;

    @Column(length = 50)
    private String email;

    @Column(columnDefinition = "TINYINT UNSIGNED", length = 20, nullable = false)
    @ColumnDefault("0")
    private int processState;

    @Column(columnDefinition = "INT UNSIGNED")
    @ColumnDefault("0")
    private int totalPrice;

    @Column(length = 100)
    private String deliveryRequest;

    @Column(length = 11, columnDefinition = "INT UNSIGNED")
    @ColumnDefault("0")
    private int deleteFl;

    private LocalDateTime depositedAt; // 입금 완료 일자
    private LocalDateTime deliveredAt; // 배송 완료 일자
    private LocalDateTime deletedAt; // 주문 취소 일자

    private String adminMemo;

    private String fullAddress;
}
