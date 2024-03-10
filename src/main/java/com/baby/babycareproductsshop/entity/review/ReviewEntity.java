package com.baby.babycareproductsshop.entity.review;

import com.baby.babycareproductsshop.entity.CreatedAtEntity;
import com.baby.babycareproductsshop.entity.order.OrderEntity;
import com.baby.babycareproductsshop.entity.product.ProductEntity;
import com.baby.babycareproductsshop.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Bean;

@Data
@Entity
@Table(name = "t_review",
        uniqueConstraints = {@UniqueConstraint(columnNames = {
                "iorder",
                "iproduct",
                "iuser"})})
public class ReviewEntity extends CreatedAtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ireview; // 옵시디언 메모 참고 - t_order_details 테이블 참조하고 있음

    // fk 안걸림(로그 출력 x)
    @ManyToOne
    @JoinColumn(name = "iorder", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "iproduct", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @Column(length = 1000)
    private String reqReviewPic;

    @Column(length = 300)
    private String contents;

    @Column(length = 5, columnDefinition = "TINYINT UNSIGNED", nullable = false)
    @ColumnDefault("'0'")
    private int productScore;

    @Column(name = "del_fl")
    @ColumnDefault("'0'")
    private int delFl;

    @Column(name = "admin_memo",length = 2500)
    private String adminMemo;

}
