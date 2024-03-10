package com.baby.babycareproductsshop.entity.product;

import com.baby.babycareproductsshop.entity.CreatedAtEntity;
import com.baby.babycareproductsshop.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "t_basket")
public class BasketEntity extends CreatedAtEntity {
    @EmbeddedId
    private BasketIds id;

    @ManyToOne
    @MapsId("iuser")
    @JoinColumn(name = "iuser", columnDefinition = "BIGINT UNSIGNED")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @ManyToOne
    @MapsId("iproduct")
    @JoinColumn(name = "iproduct", columnDefinition = "BIGINT UNSIGNED")
    private ProductEntity productEntity;

    @Column(name = "product_cnt", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private int productCount;

    @Column(name = "price", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private int price;
}
