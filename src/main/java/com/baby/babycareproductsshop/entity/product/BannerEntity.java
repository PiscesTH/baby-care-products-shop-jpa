package com.baby.babycareproductsshop.entity.product;

import com.baby.babycareproductsshop.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "t_banner")
public class BannerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long ibanner;

    @Column(length = 2500,name = "banner_pic")
    private String bannerPic;

    @Column(length = 2500,name = "banner_url" )
    private String bannerUrl;

    @Column
    @ColumnDefault("'0'")
    private int status;

    @Column(nullable = false)
    private int target;
}
