package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.admin.product.model.ReviewHideClickSelVo;
import com.baby.babycareproductsshop.admin.product.model.ReviewSearchDto;
import com.baby.babycareproductsshop.admin.product.model.SearchReviewSelVo;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.baby.babycareproductsshop.entity.product.QProductEntity.productEntity;
import static com.baby.babycareproductsshop.entity.product.QProductMiddleCategoryEntity.productMiddleCategoryEntity;
import static com.baby.babycareproductsshop.entity.review.QReviewEntity.reviewEntity;


@Slf4j
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQdslRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    @Transactional
    public List<SearchReviewSelVo> selReview(ReviewSearchDto dto, Pageable pageable) { // 리뷰검색기본그거
//        JPAQuery<SearchReviewSelVo> query = jpaQueryFactory.select(Projections.fields(SearchReviewSelVo.class,
//                        reviewEntity.userEntity.nm,
//                        reviewEntity.productEntity.repPic,
//                        reviewEntity.productEntity.iproduct,
//                        reviewEntity.productEntity.productNm,
//                        reviewEntity.contents,
//                        reviewEntity.productScore,
//                        reviewEntity.delFl)
//                )
//                .from(reviewEntity)
//                .join(reviewEntity.userEntity)
//                .join(reviewEntity.productEntity)
//                .join(productMiddleCategoryEntity).on(productMiddleCategoryEntity.eq(productEntity.middleCategoryEntity))
//                .join(productMiddleCategoryEntity.productMainCategory)
//                .where(ProductNm(dto.getKeyword()),
//                        iproduct(dto.getIproduct()),
//                        Category(dto.getImain(), dto.getImiddle()),
//                        reviewEntity.delFl.eq(0)
//                )
//                .orderBy(sortBy(dto.getSortBy()));
//        List<SearchReviewSelVo> fetch2 = query.fetch();

        List<SearchReviewSelVo> result = jpaQueryFactory.select(Projections.fields(SearchReviewSelVo.class,
                                 reviewEntity.ireview,
                                reviewEntity.userEntity.nm,
                                reviewEntity.reqReviewPic,
                                reviewEntity.productEntity.iproduct,
                                reviewEntity.productEntity.productNm,
                                reviewEntity.contents,
                                reviewEntity.productScore,
                                reviewEntity.delFl
                        )
                )
                .from(reviewEntity)
                .join(reviewEntity.userEntity)
                .join(reviewEntity.productEntity)
                .join(productMiddleCategoryEntity).on(productMiddleCategoryEntity.eq(productEntity.middleCategoryEntity))
                .join(productMiddleCategoryEntity.productMainCategory)
                .where(ProductNm(dto.getKeyword()),
                        iproduct(dto.getIproduct()),
                        Category(dto.getImain(), dto.getImiddle()),
                        reviewEntity.delFl.eq(0)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(sortBy(dto.getSortBy()))
                .fetch();

//        log.info("user {}", result.get(0).getUserEntity().getNm());
//        log.info("product {}", result.get(0).getProductEntity().getProductNm());
//        log.info("midCate {}", result.get(0).getProductEntity().getMiddleCategoryEntity());
//        log.info("mainCate {}", result.get(0).getProductEntity().getMiddleCategoryEntity().getProductMainCategory());
//
//
//        System.out.println();
        return result;
    }



    @Override
    public long countReview(ReviewSearchDto dto) {
        JPAQuery<Long> countQuery = jpaQueryFactory.select(reviewEntity.ireview.count())
                .from(reviewEntity)
                .join(reviewEntity.userEntity)
                .join(reviewEntity.productEntity)
                .join(productMiddleCategoryEntity).on(productMiddleCategoryEntity.eq(productEntity.middleCategoryEntity))
                .join(productMiddleCategoryEntity.productMainCategory)
                .where(ProductNm(dto.getKeyword()),
                        iproduct(dto.getIproduct()),
                        Category(dto.getImain(),dto.getImiddle()),
                        reviewEntity.delFl.eq(1)
                );
        return countQuery.fetchOne();
    }

    @Override
    public long totalCountReview(ReviewSearchDto dto) {
        JPAQuery<Long> countQuery = jpaQueryFactory.select(reviewEntity.ireview.count())
                .from(reviewEntity)
                .join(reviewEntity.userEntity)
                .join(reviewEntity.productEntity)
                .join(productMiddleCategoryEntity).on(productMiddleCategoryEntity.eq(productEntity.middleCategoryEntity))
                .join(productMiddleCategoryEntity.productMainCategory)
                .where(ProductNm(dto.getKeyword()),
                        iproduct(dto.getIproduct()),
                        Category(dto.getImain(),dto.getImiddle()),
                        reviewEntity.delFl.eq(0)
                );
        return countQuery.fetchOne();

    }

    @Override
    public List<SearchReviewSelVo> selReviewDel(ReviewSearchDto dto,Pageable pageable) { //숨김리뷰
        JPAQuery<SearchReviewSelVo> query = jpaQueryFactory.select(Projections.fields(SearchReviewSelVo.class,
                        reviewEntity.ireview,
                        reviewEntity.userEntity.nm,
                        reviewEntity.reqReviewPic,
                        reviewEntity.productEntity.iproduct,
                        reviewEntity.productEntity.productNm,
                        reviewEntity.contents,
                        reviewEntity.productScore,
                        reviewEntity.delFl

                        )
                )
                .from(reviewEntity)
                .join(reviewEntity.userEntity)
                .join(reviewEntity.productEntity)
                .join(productMiddleCategoryEntity).on(productMiddleCategoryEntity.eq(productEntity.middleCategoryEntity))
                .join(productMiddleCategoryEntity.productMainCategory)
                .where(ProductNm(dto.getKeyword()),
                        iproduct(dto.getIproduct()),
                        Category(dto.getImain(),dto.getImiddle()),
                        reviewEntity.delFl.eq(1)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(sortBy(dto.getSortBy()));
        return query.fetch();
    }


    @Override
    public List<ReviewHideClickSelVo> findReview(Long ireview) { // 숨김리뷰 클릭시
        JPAQuery<ReviewHideClickSelVo> query = jpaQueryFactory.select(Projections.constructor(ReviewHideClickSelVo.class,
                                reviewEntity.userEntity.nm,
                                reviewEntity.reqReviewPic,
                                reviewEntity.productEntity.productNm,
                                reviewEntity.contents,
                                reviewEntity.productScore
                        )
                )
                .from(reviewEntity)
                .rightJoin(reviewEntity.userEntity)
                .leftJoin(reviewEntity.productEntity)
                .where(reviewEntity.ireview.eq(ireview));


        return query.fetch();

    }

    private BooleanExpression ProductNm(String keyword) {
        return StringUtils.hasText(keyword) ? reviewEntity.productEntity.productNm.like("%" + keyword + "%") : null;
    }
    private BooleanExpression iproduct(Long iproduct) {
        return iproduct != 0 ? reviewEntity.productEntity.iproduct.eq(iproduct) : null;
    }

    private BooleanExpression Category(Long imain, Long imiddle) {
        if(imain != 0 && imiddle != 0) {
            return productEntity.middleCategoryEntity.productMainCategory.imain.eq(imain)

                    .and(reviewEntity.productEntity.middleCategoryEntity.imiddle.eq(imiddle));
        } else if(imain != 0) {
//            log.info("reviewEntity : {}", reviewEntity);
//            log.info("productEntity : {}", reviewEntity.productEntity);
//            log.info("middleCategoryEntity : {}", reviewEntity.productEntity.middleCategoryEntity);
//            log.info("productMainCategory : {}", reviewEntity.productEntity.middleCategoryEntity.productMainCategory);
//            QReviewEntity reviewEntity1 = reviewEntity;
//            QProductEntity productEntity1 = reviewEntity1.productEntity;
//            QProductMiddleCategoryEntity middleCategoryEntity = productEntity1.middleCategoryEntity;
//            QProductMainCategoryEntity productMainCategory = middleCategoryEntity.productMainCategory;
//            BooleanExpression eq = productMainCategory.imain.eq(imain);
//            return eq;
            return productMiddleCategoryEntity.productMainCategory.imain.eq(imain);
        } else if(imiddle != 0) {
            log.info("reviewEntity : {}", reviewEntity);
            log.info("productEntity : {}", reviewEntity.productEntity);
            log.info("middleCategoryEntity : {}", reviewEntity.productEntity.middleCategoryEntity);
            log.info("productMainCategory : {}", reviewEntity.productEntity.middleCategoryEntity.productMainCategory);
            return reviewEntity.productEntity.middleCategoryEntity.imiddle.eq(imiddle);
        } else {
            return null;
        }
    }

    protected OrderSpecifier sortBy(int sortBy) {
        OrderSpecifier orderSpecifier = null;

        switch (sortBy) {
            case 0 -> orderSpecifier = reviewEntity.productScore.desc(); // default
            case 1 -> orderSpecifier = reviewEntity.productScore.asc();
            default -> {
                return null;
            }
        }
        return orderSpecifier;
    }

}
