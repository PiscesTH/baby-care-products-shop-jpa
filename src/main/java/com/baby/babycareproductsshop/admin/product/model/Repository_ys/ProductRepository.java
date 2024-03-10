package com.baby.babycareproductsshop.admin.product.model.Repository_ys;


import com.baby.babycareproductsshop.admin.product.model.AdminProductInsDto;
import com.baby.babycareproductsshop.admin.product.model.AdminProductUptSelVo;
import com.baby.babycareproductsshop.admin.product.model.ProductGetSearchDto;
import com.baby.babycareproductsshop.admin.product.model.ProductManagementSelVo;
import com.baby.babycareproductsshop.entity.product.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long>, ProductQdslRepository  {


    List<ProductManagementSelVo> findAllByStatusAndRcFl(int rcFl,int status);

    List<ProductManagementSelVo> findAllByStatusAndNewFlAndRcFlAndPopFl(int newFl,int status,int rcFl, int popFl);

    List<ProductManagementSelVo> findAllByStatusAndPopFlAndRcFlAndNewFl(int popFl,int status,int rcfl, int newFl);

//    @Query("SELECT r FROM ReviewEntity r WHERE r.ireview = ?1")
//    String findAdminMemoByIreview(Long ireview);

//    @Query("SELECT new com.baby.babycareproductsshop.admin.product.model.AdminProductUptSelVo(r.imain,r.imiddle,r.productNm,r.productDetails,r.recommandAge,r.adminMemo,r.price,r.repPic,r.remainedCnt, p.pics) " +
//            "FROM ProductEntity r " +
//            "LEFT JOIN r. p " +
//            "WHERE r.iproduct = ?1")
//    AdminProductUptSelVo findProductSelvo(Long iproduct);



}
