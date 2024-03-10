package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.admin.product.model.SearchReviewSelVo;
import com.baby.babycareproductsshop.entity.product.ProductEntity;
import com.baby.babycareproductsshop.entity.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity,Long>, ReviewQdslRepository {

    ReviewEntity findByIreview(Long ireview);

    @Query("SELECT r.adminMemo FROM ReviewEntity r WHERE r.ireview = ?1")
    String findAdminMemoByIreview(Long ireview);


}
