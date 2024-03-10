package com.baby.babycareproductsshop.admin.product;


import com.baby.babycareproductsshop.admin.product.model.*;
import com.baby.babycareproductsshop.admin.product.model.Repository_ys.*;
import com.baby.babycareproductsshop.admin.user.model.AdminSelUserSignupVo;
import com.baby.babycareproductsshop.common.Const;
import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.entity.order.OrderDetailsEntity;
import com.baby.babycareproductsshop.entity.order.OrderEntity;
import com.baby.babycareproductsshop.entity.product.*;

import com.baby.babycareproductsshop.entity.review.ReviewEntity;
import com.baby.babycareproductsshop.entity.user.UserEntity;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.CommonErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.response.ApiResponse;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminProductService {
    private final AdminProductMapper mapper;
    private final AuthenticationFacade authenticationFacade;

    //private final JPAQueryFactory query;

    private final MyFileUtils myFileUtils;
    private final ProductPicRepository productPicRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final OrderTotalRepository orderTotalRepository;
    private final ProductRepository productRepository;
    private final BannerRepository bannerRepository;
    private final UserRepository2 userRepository2;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ReviewRepository reviewRepository;


    //상품진열관리 검색
    public List<AdminProductSearchSelVo> getSearchProductSelVo(AdminProductSearchDto dto, Pageable pageable) {
        List<AdminProductSearchSelVo> vo = productRepository.selProductAll(dto, pageable);
        long totalCount = productRepository.countRcProduct(dto);

        List<AdminProductSearchSelVo> updatedVo = vo.stream().map(item ->
                AdminProductSearchSelVo.builder()
                        .productNm(item.getProductNm())
                        .repPic(item.getRepPic())
                        .status(item.getStatus())
                        .price(item.getPrice())
                        .iproduct(item.getIproduct())
                        .totalCount(totalCount)
                        .build()
        ).collect(Collectors.toList());

        return updatedVo;
    }

    public List<AdminProductSearchSelVo> getSearchPopProductSelVo(AdminProductSearchDto dto, Pageable pageable) {
        List<AdminProductSearchSelVo> vo = productRepository.selPopProduct(dto, pageable);
        long totalCount = productRepository.countPopProduct(dto);
        List<AdminProductSearchSelVo> updatedVo = vo.stream().map(item ->
                AdminProductSearchSelVo.builder()
                        .productNm(item.getProductNm())
                        .repPic(item.getRepPic())
                        .status(item.getStatus())
                        .price(item.getPrice())
                        .iproduct(item.getIproduct())
                        .totalCount(totalCount)
                        .build()
        ).collect(Collectors.toList());
        return updatedVo;
    }

    public List<AdminProductSearchSelVo> getSearchNewProductSelVo(AdminProductSearchDto dto, Pageable pageable) {
        List<AdminProductSearchSelVo> vo = productRepository.selNewProduct(dto, pageable);
        long totalCount = productRepository.countNewProduct(dto);
        List<AdminProductSearchSelVo> updatedVo = vo.stream().map(item ->
                AdminProductSearchSelVo.builder()
                        .productNm(item.getProductNm())
                        .repPic(item.getRepPic())
                        .status(item.getStatus())
                        .price(item.getPrice())
                        .iproduct(item.getIproduct())
                        .totalCount(totalCount)
                        .build()
        ).collect(Collectors.toList());

        return updatedVo;

    }

    //------------상품진열관리 추천상품 조회
    public List<ProductManagementSelVo> getProductRc() {
        return productRepository.findAllByStatusAndRcFl(1,1);
    }

    //------------상품진열관리 신상품 조회
    public List<ProductManagementSelVo> getProductNew() {
        return productRepository.findAllByStatusAndNewFlAndRcFlAndPopFl(1,1,0,0);
    }

    //------------상품진열관리 인기상품 조회
    public List<ProductManagementSelVo> getProductPop() {
        return productRepository.findAllByStatusAndPopFlAndRcFlAndNewFl(1,1,0,0);
    }

    //------------상품진열관리 신상품 토글
    public ResVo putProductNew(Long iproduct) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(iproduct);
        ProductEntity entity = productEntityOptional.get();
        entity.setStatus(entity.getStatus() == 0 ? 1 : 0);
        productRepository.save(entity);
        return new ResVo(entity.getStatus() == 0 ? Const.FAIL : Const.SUCCESS);
    }

    //------------상품진열관리 인기상품 토글
    public ResVo putProductPop(Long iproduct) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(iproduct);
        ProductEntity entity = productEntityOptional.get();
        entity.setStatus(entity.getStatus() == 0 ? 1 : 0);
        productRepository.save(entity);
        return new ResVo(entity.getStatus() == 0 ? Const.FAIL : Const.SUCCESS);
    }

    //------------상품진열관리 추천상품 토글
    public ResVo putProductRc(Long iproduct) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(iproduct);
        ProductEntity entity = productEntityOptional.get();
        entity.setRcFl(entity.getRcFl() == 0 ? 1 : 0);
        entity.setStatus(entity.getStatus() == 0 ? 1 : 0);
        productRepository.save(entity);
        return new ResVo(entity.getStatus() == 0 ? Const.FAIL : Const.SUCCESS);
    }

    // -------------- 상품등록
    @Transactional
    public ResVo postProduct(List<MultipartFile> pics, MultipartFile productDetails, AdminProductInsDto dto) {

        try {
//            if (dto.getProductNm().isBlank() ) {
//                throw new RestApiException(AuthErrorCode.PRODUCT_NM_IS_BLANK);
//            }
//            if (dto.getPrice() == 0 ) {
//                throw new RestApiException(AuthErrorCode.PRODUCT_PRICE_IS_ZERO);
//            }
            ProductEntity entity = new ProductEntity();
            ProductMiddleCategoryEntity middleCategoryEntity = middleCategoryRepository.findByImiddleAndProductMainCategory_Imain(dto.getImiddle(), dto.getImain());
            entity.setMiddleCategoryEntity(middleCategoryEntity);
            entity.setProductNm(dto.getProductNm());
            entity.setRecommandAge(dto.getRecommendedAge());
            entity.setPrice(dto.getPrice());
            entity.setAdminMemo(dto.getAdminMemo());
            entity.setRecommandAge(dto.getRemainedCnt());
            entity.setNewFl(1);
            productRepository.save(entity);
            String target = "/product/" + entity.getIproduct();

            String detailsFileNm = myFileUtils.transferTo(productDetails, target);
            entity.setProductDetails(detailsFileNm);

            ProductEntity savedEntity = productRepository.save(entity);
            for (MultipartFile pic : pics) {
                String fileNm = myFileUtils.transferTo(pic, target);
                ProductPicEntity productPicEntity = new ProductPicEntity();
                productPicEntity.setProductPic(fileNm);
                productPicEntity.setProductEntity(savedEntity);
                if (entity.getRepPic() == null) {
                    entity.setRepPic(fileNm);
                }
                productPicRepository.save(productPicEntity);
            }
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            return new ResVo(Const.FAIL);
        }
    }

    // 상품 수정
    public ResVo putProduct(List<MultipartFile> pics, MultipartFile productDetails, AdminProductUptDto dto, Long iproduct) {
        try {
//            if (dto.getProductNm().isBlank() ) {
//                throw new RestApiException(AuthErrorCode.PRODUCT_NM_IS_BLANK);
//            }
//            if (dto.getPrice() == 0 ) {
//                throw new RestApiException(AuthErrorCode.PRODUCT_PRICE_IS_ZERO);
//            }
            ProductEntity entity = productRepository.findById(iproduct).get();
//            ProductMainCategoryEntity MainCategoryEntity = mainCategoryRepository.findById(dto.getImain()).get();
//            ProductMiddleCategoryEntity middleCategoryEntity = middleCategoryRepository.findById(dto.getImiddle()).get();
            ProductMiddleCategoryEntity middleCategoryEntity = middleCategoryRepository.findByImiddleAndProductMainCategory_Imain(dto.getImiddle(), dto.getImain());
            entity.setMiddleCategoryEntity(middleCategoryEntity);
//
//            middleCategoryEntity.setProductMainCategory(MainCategoryEntity);
//            middleCategoryEntity.setImiddle(dto.getImiddle());

            entity.setProductNm(dto.getProductNm());
            entity.setRecommandAge(dto.getRecommendedAge());
            entity.setPrice(dto.getPrice());
            entity.setAdminMemo(dto.getAdminMemo());
            entity.setRemainedCnt(dto.getRemainedCnt());
            entity.setNewFl(dto.getNewFl());
            entity.setPopFl(dto.getPopFl());
            productRepository.save(entity);

            if (dto.getDelPics() != null && !dto.getDelPics().isEmpty()) {
                List<ProductPicEntity> productPics = productPicRepository.findByProductEntity_Iproduct(entity.getIproduct());
                List<ProductPicEntity> delProdPics = productPics.stream()
                        .filter(pic -> dto.getDelPics().contains(pic.getProductPic()))
                        .collect(Collectors.toList());

                // 실제 파일 삭제
                for (ProductPicEntity pic : delProdPics) {
                    String target = "/product/" + entity.getIproduct() + "/" + pic.getProductPic();
                    myFileUtils.delFile(target); // 지정한 이미지 삭제
                    productPicRepository.delete(pic);
                    //productPics.remove(pic);
                }
            }

            String target = "/product/" + entity.getIproduct();
            if (productDetails != null && !productDetails.isEmpty()) {
                String detailsFileNm = myFileUtils.transferTo(productDetails, target);
                entity.setProductDetails(detailsFileNm);
            }
            ProductEntity savedEntity = productRepository.save(entity);

            if (pics != null && !pics.isEmpty()) {
                for (MultipartFile file : pics) {
                    if (file != null && !file.isEmpty()) {
                        String fileNm = myFileUtils.transferTo(file, target);
                        ProductPicEntity productPicEntity = new ProductPicEntity();
                        productPicEntity.setProductPic(fileNm);
                        productPicEntity.setProductEntity(savedEntity);
                        //entity.setRepPic(fileNm);

                        productPicRepository.save(productPicEntity);
                    }
                }
            }
            List<ProductPicEntity> findProductPic = productPicRepository.findByProductEntity(entity);
            entity.setRepPic(findProductPic.get(0).getProductPic());
            productRepository.save(entity);

            return new ResVo(Const.SUCCESS);
        } catch(Exception e) {
            return new ResVo(Const.FAIL);
        }
    }
    //------------------------------------------------------- 상품삭제
    @Transactional
    public ResVo delProduct(List<Long> iproduct) {
        List<ProductEntity> products = productRepository.findAllById(iproduct);
        if (!products.isEmpty()) {
            for (ProductEntity product : products) {
                product.setDelFl(1);
            }
            productRepository.saveAll(products);
            return new ResVo(Const.SUCCESS);
        }
        return new ResVo(Const.FAIL);
    }

    //상품검색
    public List<ProductGetSearchSelVo> getSearchProductSelVo(ProductGetSearchDto dto, Pageable pageable) {
        long totalCount = productRepository.countSearchProduct(dto);

        List<AdminProductPicUptSelVo> picsList = productRepository.selProductPicUptSelVo();
        List<ProductGetSearchSelVo> products = productRepository.findProduct(dto, pageable);
        return products.stream().map(item -> {
            List<String> pics = picsList.stream()
                    .filter(pic -> pic.getIproduct().equals(item.getIproduct()))
                    .map(AdminProductPicUptSelVo::getProductPic)
                    .collect(Collectors.toList());

                    return ProductGetSearchSelVo.builder()
                            .productPic(pics)
                            .price(item.getPrice())
                            .adminMemo(item.getAdminMemo())
                            .productNm(item.getProductNm())
                            .imiddle(item.getImiddle())
                            .imain(item.getImain())
                            .productDetails(item.getProductDetails())
                            .iproduct(item.getIproduct())
                            .recommandAge(item.getRecommandAge())
                            .remainedCnt(item.getRemainedCnt())
                            .totalCount(totalCount)
                            .build();
                }
        ).collect(Collectors.toList());
    }

    //------------배너출력-------------------------
    public List<BannerSelVo> getBanner() {
        return bannerRepository.bannerSelVo();
    }

    //------------ 배너수정
    @Transactional
    public ResVo updateBanner(Long ibanner, MultipartFile pic, BannerInsDto dto) {
        try {
            BannerEntity banner = bannerRepository.findById(ibanner).get();
            banner.setBannerUrl(dto.getBannerUrl());
            banner.setTarget(dto.getTarget());
            banner.setStatus(dto.getStatus());
            bannerRepository.save(banner);
            if (pic != null) {
                String target = "/banner/" + banner.getIbanner();
                myFileUtils.delDirTrigger(target);
                String savedPic = myFileUtils.transferTo(pic, target);
                banner.setBannerPic(savedPic);
            }
            bannerRepository.save(banner);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            return new ResVo(Const.FAIL);
        }
    }

    //-----------배너 생성
    @Transactional
    public ResVo postBanner(MultipartFile pic, BannerInsDto dto) {
        try {
            BannerEntity banner = new BannerEntity();
            banner.setBannerUrl(dto.getBannerUrl());
            banner.setTarget(dto.getTarget());
            banner.setStatus(dto.getStatus());
            bannerRepository.save(banner);
            String savedPic = myFileUtils.transferTo(pic, "/banner/" + banner.getIbanner());
            banner.setBannerPic(savedPic);
            bannerRepository.save(banner);

            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            return new ResVo(Const.FAIL);
        }
    }

    //-------배너삭제
    @Transactional
    public ResVo delBanner(Long ibanner) {
        String target = "/banner/" + ibanner;
        myFileUtils.delDirTrigger(target);
        int result = bannerRepository.deleteAllByIbanner(ibanner);
        if (result == 1) {
            return new ResVo(result);
        }
        return new ResVo(Const.FAIL);
    }

    //------리뷰 검색
    public List<SearchReviewSelVo> getSearchReview(ReviewSearchDto dto, Pageable pageable) {
        List<SearchReviewSelVo> reviews = reviewRepository.selReview(dto, pageable);
        long totalCount = reviewRepository.totalCountReview(dto);
        return reviews.stream()
                .map(review -> SearchReviewSelVo.builder()
                        .ireview(review.getIreview())
                        .nm(review.getNm())
                        .reqReviewPic(review.getReqReviewPic())
                        .iproduct(review.getIproduct())
                        .productNm(review.getProductNm())
                        .contents(review.getContents())
                        .productScore(review.getProductScore())
                        .delFl(review.getDelFl())
                        .totalCount(totalCount)
                        .build())
                .collect(Collectors.toList());
    }
    // 숨겼던 리뷰 검색
    public List<SearchReviewSelVo> getHiddenReview(ReviewSearchDto dto, Pageable pageable) {
        List<SearchReviewSelVo> reviews = reviewRepository.selReviewDel(dto, pageable);
        long totalCount = reviewRepository.countReview(dto);
        return reviews.stream()
                .map(review -> SearchReviewSelVo.builder()
                        .ireview(review.getIreview())
                        .nm(review.getNm())
                        .reqReviewPic(review.getReqReviewPic())
                        .iproduct(review.getIproduct())
                        .productNm(review.getProductNm())
                        .contents(review.getContents())
                        .productScore(review.getProductScore())
                        .delFl(review.getDelFl())
                        .totalCount(totalCount)
                        .build())
                .collect(Collectors.toList());

    }

    //관리자 메모 작성&수정
    public ResVo postReviewAdminMemo(ReviewMemoInsDto dto) {
        ReviewEntity entity = reviewRepository.findByIreview(dto.getIreview());
        if (entity != null) {
            entity.setAdminMemo(dto.getAdminMemo());
            reviewRepository.save(entity);
            return new ResVo(Const.SUCCESS);
        }
        return new ResVo(Const.FAIL);
    }

    //숨김리뷰 토글처ㅣㄹ
    public ResVo putReviewTogle(Long ireview) {
        Optional<ReviewEntity> optionalReview = reviewRepository.findById(ireview);
        ReviewEntity entity = optionalReview.get();
        entity.setDelFl(optionalReview.get().getDelFl() == 0 ? 1 : 0);
        reviewRepository.save(entity);
        return new ResVo(entity.getDelFl() == 0 ? Const.FAIL : Const.SUCCESS);
    }

    // 리뷰클릭시 뜨는창
    public List<ReviewHideClickSelVo> getHiCkSelVo(Long ireview) {
        return reviewRepository.findReview(ireview);
    }

    //관리자 메모만
    public String getReviewMeMo(Long ireview) {
        String vo = reviewRepository.findAdminMemoByIreview(ireview);
        return vo;
    }

    // 취소수
    public List<AdminRefundReturnSelVo> OrderCancelSelVo(AdminRefundReturnDto dto) {
        List<OrderEntity> entityList = orderTotalRepository.refundReturnSelVo(dto);
        Map<String, AdminRefundReturnSelVo> map = new HashMap<>();
        int totalRegisterCnt = 0;

        for (OrderEntity entity : entityList) {
            totalRegisterCnt += entity.getDeleteFl();
            //totalRegisterCnt ++;
        }
        List<AdminRefundReturnSelVo> result = new ArrayList<>();
        for (OrderEntity entity : entityList) {

                AdminRefundReturnSelVo vo = new AdminRefundReturnSelVo();
                vo.setRegisterCnt(entity.getDeleteFl());
                vo.setUpdatedAt(entity.getUpdatedAt());
                vo.setDate(Utils.getDate(dto.getYear(), dto.getMonth(), vo));
                vo.setTotalRegisterCnt(totalRegisterCnt);
                vo.setRegisterRate(String.format("%.2f", (double) vo.getRegisterCnt() / totalRegisterCnt));

                map.put(Utils.getDate(dto.getYear(), dto.getMonth(), vo), vo);
                result.add(vo);
        }
        if (dto.getYear() == 0 && dto.getMonth() == 0) {
            return result;
        }

        int date = Utils.getDaysOrMonths(dto.getYear(), dto.getMonth());

        for (int i = 1; i <= date; i++) {
            String key = Utils.getKey(dto.getYear(), dto.getMonth(), i);
            AdminRefundReturnSelVo vo = map.get(key);
            if (vo == null) {
                map.put(key, new AdminRefundReturnSelVo(key));
            }
        }
        result = map.values().stream().sorted().toList();
        return result;
    }
    // 반품
    public List<AdminRefundReturnSelVo> OrderReturnSelVo(AdminRefundReturnDto dto) {
        List<OrderDetailsEntity> entityList = orderDetailsRepository.refundReturnSelVo(dto);
        Map<String, AdminRefundReturnSelVo> map = new HashMap<>();
        int totalRegisterCnt = 0;
        for (OrderDetailsEntity entity : entityList) {
            totalRegisterCnt += entity.getRefundFl();
            //totalRegisterCnt ++;
        }
        List<AdminRefundReturnSelVo> result = new ArrayList<>();
        for (OrderDetailsEntity entity : entityList) {
            AdminRefundReturnSelVo vo = new AdminRefundReturnSelVo();
            vo.setRegisterCnt(entity.getRefundFl());
            vo.setUpdatedAt(entity.getUpdatedAt());
            vo.setDate(Utils.getDate(dto.getYear(), dto.getMonth(), vo));
            vo.setTotalRegisterCnt(totalRegisterCnt);
//            if (vo.getRegisterCnt() == 0 ) {
//                vo.setRegisterRate(String.valueOf(0));
//            }
            vo.setRegisterRate(String.format("%.2f", (double) vo.getRegisterCnt() / totalRegisterCnt));

            map.put(Utils.getDate(dto.getYear(), dto.getMonth(), vo), vo);
            result.add(vo);
        }

        if (dto.getYear() == 0 && dto.getMonth() == 0) {
            return result;
        }
        int date = Utils.getDaysOrMonths(dto.getYear(), dto.getMonth());
        for (int i = 1; i <= date; i++) {
            String key = Utils.getKey(dto.getYear(), dto.getMonth(), i);
            AdminRefundReturnSelVo vo = map.get(key);
            if (vo == null) {
                map.put(key, new AdminRefundReturnSelVo(key));
            }
        }
        result = map.values().stream().sorted().toList();
        return result;
    }

//    public List<AdminProductUptSelVo> getProduct(Long iproduct) {
//        return productRepository.selProductUptSelVo(iproduct);
//    }
//
//    public List<AdminProductPicUptSelVo> getProductPic(Long iproduct) {
//        return productRepository.selProductPicUptSelVo(iproduct);
//    }

//    public List<AdminProductUptDate> getProductDate(Long iproduct) {
//        List<AdminProductUptSelVo> result = productRepository.selProductUptSelVo(iproduct);
//        List<AdminProductPicUptSelVo> result2 = productRepository.selProductPicUptSelVo(iproduct);
//        List<AdminProductUptDate> productUpdateList = result.stream().map(uptSelVo -> {
//            List<String> matchingPics = result2.stream()
//                    .filter(picUptSelVo -> picUptSelVo.getIproduct() == uptSelVo.getIproduct()) //
//                    .map(AdminProductPicUptSelVo::getProductPic)
//                    .collect(Collectors.toList());
//            return AdminProductUptDate.builder()
//                    .imain(uptSelVo.getImain())
//                    .imiddle(uptSelVo.getImiddle())
//                    .productNm(uptSelVo.getProductNm())
//                    .productDetails(uptSelVo.getProductDetails())
//                    .recommandAge(uptSelVo.getRecommendedAge())
//                    .adminMemo(uptSelVo.getAdminMemo())
//                    .price(uptSelVo.getPrice())
//                    //.repPic(uptSelVo.getRepPic())
//                    .remainedCnt(uptSelVo.getRemainedCnt())
//                    .productPic(matchingPics)
//                    .build();
//        }).collect(Collectors.toList());
//        return productUpdateList;
//    }
}


