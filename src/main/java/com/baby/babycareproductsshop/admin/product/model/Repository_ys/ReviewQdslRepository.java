package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.admin.product.model.ReviewHideClickSelVo;
import com.baby.babycareproductsshop.admin.product.model.ReviewSearchDto;
import com.baby.babycareproductsshop.admin.product.model.SearchReviewSelVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQdslRepository {

    List<SearchReviewSelVo> selReview(ReviewSearchDto dto,Pageable pageable);
    long countReview(ReviewSearchDto dto);

    long totalCountReview(ReviewSearchDto dto);
    List<SearchReviewSelVo> selReviewDel(ReviewSearchDto dto, Pageable pageable);


    List<ReviewHideClickSelVo> findReview(Long ireview);








}
