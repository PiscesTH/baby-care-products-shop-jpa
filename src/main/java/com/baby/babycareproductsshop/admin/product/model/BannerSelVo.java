package com.baby.babycareproductsshop.admin.product.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BannerSelVo {
    private Long ibanner;
    private int target;
    private int status;
    private String bannerUrl;
    private String bannerPic;
}
