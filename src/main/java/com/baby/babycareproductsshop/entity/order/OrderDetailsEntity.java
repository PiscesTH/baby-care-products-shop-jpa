package com.baby.babycareproductsshop.entity.order;

import com.baby.babycareproductsshop.entity.BaseEntity;
import com.baby.babycareproductsshop.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_order_details",
        uniqueConstraints = {@UniqueConstraint(columnNames = {
                "iorder",
                "iproduct"})})
public class OrderDetailsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, columnDefinition = "BIGINT UNSIGNED")
    private Long idetails;

    @ManyToOne
    @JoinColumn(name = "iorder", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "iproduct", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private ProductEntity productEntity;

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    private int productCnt;

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    private int productPrice;

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    private int productTotalPrice;

    @Column(columnDefinition = "TINYINT", nullable = false)
    @ColumnDefault("'0'")
    private int refundFl;

    private LocalDateTime refundedAt;
}
