package com.baby.babycareproductsshop.entity.refund;

import com.baby.babycareproductsshop.entity.BaseEntity;
import com.baby.babycareproductsshop.entity.order.OrderDetailsEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_refund")
public class RefundEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long irefund;

    @ManyToOne
    @JoinColumn(name = "idetails", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private OrderDetailsEntity orderDetailsEntity;

    @Column(length = 200, nullable = false)
    private String contents;

    @Column(length = 11, nullable = false)
    private int refundCnt;

    @Column(length = 11, nullable = false)
    private int refundPrice;

    @Column(length = 4, columnDefinition = "TINYINT UNSIGNED", nullable = false)
    @ColumnDefault("'0'")
    private int complateFl;
}
