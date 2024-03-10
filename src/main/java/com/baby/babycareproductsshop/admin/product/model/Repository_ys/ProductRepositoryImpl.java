package com.baby.babycareproductsshop.admin.product.model.Repository_ys;

import com.baby.babycareproductsshop.admin.product.model.*;
import com.baby.babycareproductsshop.entity.product.ProductEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

import static com.baby.babycareproductsshop.entity.product.QProductEntity.productEntity;
import static com.baby.babycareproductsshop.entity.product.QProductPicEntity.productPicEntity;
import static com.baby.babycareproductsshop.entity.review.QReviewEntity.reviewEntity;

//import static com.baby.babycareproductsshop.entity.product.QProductEntity.productEntity;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductQdslRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override// 진열관리 추천상품검색
    public List<AdminProductSearchSelVo> selProductAll(AdminProductSearchDto dto, Pageable pageable) {
        JPAQuery<AdminProductSearchSelVo> query = jpaQueryFactory.select(Projections.fields(AdminProductSearchSelVo.class,
                                productEntity.productNm
                                ,productEntity.iproduct
                                ,productEntity.price
                                ,productEntity.repPic
                                ,productEntity.status
                        )
                )
                .where(productNm(dto.getKeyword())
                        ,iproduct(dto.getIproduct())
                        ,category(dto.getImain(),dto.getImiddle())
                        ,allProductRcSelVo()

                )
                .from(productEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return query.fetch();
    }

    @Override //진열관리 인기 검색
    public List<AdminProductSearchSelVo> selPopProduct(AdminProductSearchDto dto, Pageable pageable) {
        JPAQuery<AdminProductSearchSelVo> query = jpaQueryFactory.select(Projections.fields(AdminProductSearchSelVo.class,
                                productEntity.productNm
                                ,productEntity.iproduct
                                ,productEntity.price
                                ,productEntity.repPic
                                ,productEntity.status
                        )
                )
                .where(productNm(dto.getKeyword())
                        ,iproduct(dto.getIproduct())
                        ,category(dto.getImain(),dto.getImiddle())
                        ,productEntity.status.eq(0).and(productEntity.popFl.eq(1).and(productEntity.newFl.eq(0).and(productEntity.rcFl.eq(0))))//인기상품만
                                        .or(productEntity.status.eq(0).and(productEntity.popFl.eq(1).and(productEntity.newFl.eq(1).and(productEntity.rcFl.eq(0)))) //인기상품이면서도 신상품
                                                        .or(productEntity.status.eq(1).and(productEntity.popFl.eq(1)).and(productEntity.newFl.eq(0).and(productEntity.rcFl.eq(0))))
                                                )


                )
                .from(productEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return query.fetch();
    }

    @Override//진열관리 신상품 검색
    public List<AdminProductSearchSelVo> selNewProduct(AdminProductSearchDto dto, Pageable pageable) {
        JPAQuery<AdminProductSearchSelVo> query = jpaQueryFactory.select(Projections.fields(AdminProductSearchSelVo.class,
                                productEntity.productNm
                                ,productEntity.iproduct
                                ,productEntity.price
                                ,productEntity.repPic
                                ,productEntity.status
                        )
                )
                .where(productNm(dto.getKeyword())
                        ,iproduct(dto.getIproduct())
                        ,category(dto.getImain(),dto.getImiddle())
                        ,productEntity.status.eq(0).and(productEntity.popFl.eq(0).and(productEntity.newFl.eq(1).and(productEntity.rcFl.eq(0))))
                                        .or(productEntity.status.eq(0).and(productEntity.popFl.eq(1).and(productEntity.newFl.eq(1).and(productEntity.rcFl.eq(0))))
                                                        .or(productEntity.status.eq(1).and(productEntity.newFl.eq(1)).and(productEntity.popFl.eq(0).and(productEntity.rcFl.eq(0))))
                                                )


                )
                .from(productEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return query.fetch();

    }
    // 상품 수정시 필요한 데이터 불러오는거
    @Override
    public List<AdminProductUptSelVo> selProductUptSelVo(Long iproduct) {
        JPAQuery<AdminProductUptSelVo> query = jpaQueryFactory.select(Projections.fields(AdminProductUptSelVo.class
                                ,productEntity.middleCategoryEntity.productMainCategory.imain
                                ,productEntity.middleCategoryEntity.imiddle
                                ,productEntity.productNm
                                ,productEntity.productDetails
                                ,productEntity.recommandAge
                                ,productEntity.adminMemo
                                ,productEntity.price
                                ,productEntity.repPic
                                ,productEntity.remainedCnt
                        )
                )
                .from(productEntity)
                .leftJoin(productPicEntity).on(productEntity.iproduct.eq(productPicEntity.iproduct))
                .where(productEntity.iproduct.eq(iproduct));
        return query.fetch();
    }

    @Override
    public List<AdminProductPicUptSelVo> selProductPicUptSelVo() {
        JPAQuery<AdminProductPicUptSelVo> query = jpaQueryFactory.select(Projections.fields(AdminProductPicUptSelVo.class
                                ,productPicEntity.productPic
                                ,productPicEntity.productEntity.iproduct
                        )
                )
                .from(productPicEntity);
        return query.fetch();
    }

    @Override
    public long countSearchProduct(ProductGetSearchDto dto) {
        JPAQuery<Long> totalCountQuery = jpaQueryFactory.select(productEntity.count())
                .where(productNm(dto.getKeyword()),
                        iproduct(dto.getIproduct()),
                        category(dto.getImain(),dto.getImiddle()),
                        productEntity.delFl.eq(0),
                        price(dto.getMinPrice(),dto.getMaxPrice()),
                        searchDateFilter(dto.getSearchStartDate(),dto.getSearchEndDate())
                )
                .from(productEntity);
        return totalCountQuery.fetchOne();
    }

    @Override
    public long countRcProduct(AdminProductSearchDto dto) {
        JPAQuery<Long> countQuery = jpaQueryFactory.select(productEntity.count())
                .where(productNm(dto.getKeyword()),
                        iproduct(dto.getIproduct()),
                        category(dto.getImain(),dto.getImiddle())
                )
                .from(productEntity);

        return countQuery.fetchOne();
    }

    @Override
    public long countPopProduct(AdminProductSearchDto dto) {
        JPAQuery<Long> countQuery = jpaQueryFactory.select(productEntity.count())
                .where(productNm(dto.getKeyword()),
                        iproduct(dto.getIproduct()),
                        category(dto.getImain(),dto.getImiddle()),
                        productEntity.popFl.eq(1)
                )
                .from(productEntity);

        return countQuery.fetchOne();
    }

    @Override
    public long countNewProduct(AdminProductSearchDto dto) {
        JPAQuery<Long> countQuery = jpaQueryFactory.select(productEntity.count())
                .where(productNm(dto.getKeyword()),
                        iproduct(dto.getIproduct()),
                        category(dto.getImain(),dto.getImiddle()),
                        productEntity.newFl.eq(1)
                )
                .from(productEntity);
        return countQuery.fetchOne();
    }

//--------------------------------------------------------------------------------------------------------

    //상품검색
    @Override
    public List<ProductGetSearchSelVo> findProduct(ProductGetSearchDto dto,Pageable pageable) {
        JPAQuery<ProductGetSearchSelVo> query = jpaQueryFactory.select(Projections.fields(ProductGetSearchSelVo.class,
                        productEntity.productNm
                        ,productEntity.iproduct
                        ,productEntity.price
                        ,productEntity.remainedCnt
                        ,productEntity.middleCategoryEntity.productMainCategory.imain
                        ,productEntity.middleCategoryEntity.imiddle
                        ,productEntity.recommandAge
                        ,productEntity.productDetails
                        ,productEntity.adminMemo )
                )
                .where(productNm(dto.getKeyword())
                        ,iproduct(dto.getIproduct())
                        ,category(dto.getImain(),dto.getImiddle())
                        // ,dateSelectSearch(dto.getDateFl())
                        ,productEntity.delFl.eq(0)
                        ,price(dto.getMinPrice(),dto.getMaxPrice())
                        ,searchDateFilter(dto.getSearchStartDate(),dto.getSearchEndDate())
                )
                .from(productEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(productEntity.createdAt.desc());
        return query.fetch();
    }

    //*---------------------------------------------------------------------------------


    private BooleanExpression productNm(String keyword) { //상품이름
        return StringUtils.hasText(keyword) ? productEntity.productNm.contains(keyword) : null;
    }

    private BooleanExpression iproduct(Long iproduct) { //상품코드
        return iproduct != 0 ? productEntity.iproduct.eq(iproduct) : null;
    }
    private BooleanExpression category(Long imain, Long imiddle) { //카테고리
        if(imain != 0 && imiddle != 0) {
            return productEntity.middleCategoryEntity.productMainCategory.imain.eq(imain)
                    .and(productEntity.middleCategoryEntity.imiddle.eq(imiddle));
        } else if(imain != 0) {
            return productEntity.middleCategoryEntity.productMainCategory.imain.eq(imain);
        } else if(imiddle != 0) {
            return productEntity.middleCategoryEntity.imiddle.eq( imiddle);
        } else {
            return null;
        }
    }
    private BooleanExpression price(int minPrice, int maxPrice) {
        if (minPrice != 0 && maxPrice != 0) {
            return productEntity.price.between(minPrice, maxPrice);
        }
        if (minPrice != 0) {
            return productEntity.price.goe(minPrice);
        }
        if (maxPrice != 0) {
            return productEntity.price.loe(maxPrice);
        }
        return null;
    }
    private BooleanExpression searchDateFilter(LocalDate searchStartDate, LocalDate searchEndDate) {
        if(searchStartDate == null || searchEndDate == null){
            return null;
        }
        if(searchStartDate.isAfter(searchEndDate)){
            return null;
        }
        try{
            BooleanExpression isGoeStartDate = productEntity.createdAt.goe(LocalDateTime.of(searchStartDate, LocalTime.MIN));
            BooleanExpression isLoeEndDate = productEntity.createdAt.loe(LocalDateTime.of(searchEndDate, LocalTime.MAX).withNano(0));
            return Expressions.allOf(isGoeStartDate, isLoeEndDate);
        }catch(Exception e){
            return null;
        }
    }

    private BooleanExpression dateSelectSearch(int dateFl) {
        BooleanExpression booleanExpression = null;

        switch (dateFl) {
            case 1 -> booleanExpression = productEntity.createdAt.between(todayStartTime(), todayEndTime());
            case 2 -> booleanExpression = productEntity.createdAt.between(yesterdayStartTime(), yesterdayEndTime());
            case 3 -> booleanExpression = productEntity.createdAt.between(todayStartTime().minusDays(7), todayEndTime());
            case 4, 5 -> booleanExpression = productEntity.createdAt.between(monthStartDay(), monthEndDay());
            case 6 -> booleanExpression = productEntity.createdAt.between(monthStartDay().minusMonths(3), monthEndDay().minusMonths(3));
            default -> {
                return null;
            }
        }
        return booleanExpression;
    }

    // 아래는 어제, 오늘 시작 시간과 끝 시간 조건문 모음
    private LocalDateTime todayStartTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    private LocalDateTime todayEndTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }

    private LocalDateTime yesterdayStartTime() {
        return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);
    }

    private LocalDateTime yesterdayEndTime() {
        return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);
    }

    private LocalDateTime monthStartDay() {
        return LocalDateTime.of(YearMonth.now().minusMonths(1).atDay(1), LocalTime.MIN);
    }

    private LocalDateTime monthEndDay() {
        return LocalDateTime.of(YearMonth.now().minusMonths(1).atDay(31), LocalTime.MAX);
    }
    private BooleanExpression allProductRcSelVo() {
        return productEntity.status.eq(0).and(productEntity.popFl.eq(0).and(productEntity.newFl.eq(0).and(productEntity.rcFl.eq(0)))) //모든상품
                .or(productEntity.status.eq(0).and(productEntity.popFl.eq(0).and(productEntity.newFl.eq(0).and(productEntity.rcFl.eq(1)))) // 추천상품만
                        .or(productEntity.status.eq(0).and(productEntity.popFl.eq(0).and(productEntity.newFl.eq(1).and(productEntity.rcFl.eq(0)))) //신상품만
                                .or(productEntity.status.eq(0).and(productEntity.popFl.eq(0).and(productEntity.newFl.eq(1).and(productEntity.rcFl.eq(1)))) //신상품이면서도 추천상품
                                        .or(productEntity.status.eq(0).and(productEntity.popFl.eq(1).and(productEntity.newFl.eq(0).and(productEntity.rcFl.eq(0))))//인기상품만
                                                .or(productEntity.status.eq(0).and(productEntity.popFl.eq(1).and(productEntity.newFl.eq(0).and(productEntity.rcFl.eq(1)))) //인기상품이면서도 추천상품
                                                        .or(productEntity.status.eq(0).and(productEntity.popFl.eq(1).and(productEntity.newFl.eq(1).and(productEntity.rcFl.eq(0)))) //인기상품이면서도 신상품
                                                                .or(productEntity.status.eq(0).and(productEntity.popFl.eq(1).and(productEntity.newFl.eq(1).and(productEntity.rcFl.eq(1)))) //인기상품이면서도 신상품이며 추천상품
                                                                        .or(productEntity.status.eq(1).and(productEntity.popFl.eq(0).and(productEntity.newFl.eq(0).and(productEntity.rcFl.eq(1)))))))))))); // 추천 진열한거))))))));
    }
        // return productEntity.status.ne(1).and(productEntity.popFl.ne(1)).or(productEntity.status.ne(1).and(productEntity.newFl.ne(1)));

//    }
//    BooleanExpression condition1 = productEntity.status.ne(1).and(productEntity.popFl.ne(1));
//    BooleanExpression condition2 = productEntity.status.ne(1).and(productEntity.newFl.ne(1));


}


