package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.admin.product.model.BannerSelVo;
import com.baby.babycareproductsshop.entity.product.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<BannerEntity,Long>  {
    int deleteAllByIbanner(Long ibanner);

    @Query("SELECT new com.baby.babycareproductsshop.admin.product.model.BannerSelVo(b.ibanner, b.target, b.status, b.bannerUrl, b.bannerPic) FROM BannerEntity b")
    List<BannerSelVo> bannerSelVo();


}
