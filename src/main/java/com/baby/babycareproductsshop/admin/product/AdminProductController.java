package com.baby.babycareproductsshop.admin.product;
import com.baby.babycareproductsshop.admin.product.model.*;
import com.baby.babycareproductsshop.common.ResVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.baby.babycareproductsshop.common.Const.PAGE_SIZE;

@Slf4j
@RestController
@RequestMapping("/api/admin/product")
@RequiredArgsConstructor
@Tag(name = "관리자 기능 - 상품 관련 API")
public class AdminProductController {
    private final AdminProductService service;

    public Pageable pageable(int page) {
        return PageRequest.of(page, PAGE_SIZE);
    }


    //-----------------------------------------------------------------상품 등록------------------------------------------------------
    @PostMapping("/product")
    @Operation(summary = "상품등록 ")
    public ResVo postProduct(@RequestPart(name = "pics") List<MultipartFile> pics,
                             @RequestPart(name = "productDetails") MultipartFile productDetails,
                             @RequestPart @Valid AdminProductInsDto dto) {
        log.info("dto : {}", dto);
        return service.postProduct(pics, productDetails, dto);
    }
    //-----------------------------------------------------------------상품 수정------------------------------------------------------

    @PatchMapping("/product")
    @Operation(summary = "상품 수정 ")
    public ResVo uptProduct (@RequestPart(required = false) List<MultipartFile> pics,
                             @RequestPart(required = false) MultipartFile productDetails,
                             @RequestPart @Valid AdminProductUptDto dto,@RequestParam Long iproduct) {
        return service.putProduct(pics,productDetails,dto,iproduct);
    }
    //-----------------------------------------------------------------상품 삭제------------------------------------------------------
    @DeleteMapping("/product")
    @Operation(summary = "상품삭제 ")
    public ResVo delProduct(@RequestParam List<Long> iproduct) {
        return service.delProduct(iproduct);
    }
    //-----------------------------------------------------------------상품 검색------------------------------------------------------

    @GetMapping("/productSearch")
    @Operation(summary = "상품검색 ")
    public List<ProductGetSearchSelVo> getSearchProductSelVo(ProductGetSearchDto dto) {
        return service.getSearchProductSelVo(dto,pageable(dto.getPage()));
    }

//    @GetMapping("/productUptDate")
//    @Operation(summary = "상품 수정 시 기존 데이터 호출 ")
//    public List<AdminProductUptDate> getProductDate(Long iproduct) {
//        return service.getProductDate(iproduct);
//    }



    //-----------------------------------------------------------------상품진열관리 추천상품 조회------------------------------------------------------
    @GetMapping("/productRc")
    @Operation(summary = "진열관리 추천상품 조회")
    public List<ProductManagementSelVo> getProductRc() {
        return service.getProductRc();
    }
    //-----------------------------------------------------------------상품진열관리 신상품 조회------------------------------------------------------
    @GetMapping("/productNew")
    @Operation(summary = "진열관리 신상품 조회")
    public List<ProductManagementSelVo> getProductNew() {
        return service.getProductNew();
    }
    //-----------------------------------------------------------------상품진열관리 인기상품 조회------------------------------------------------------
    @GetMapping("/productPop")
    @Operation(summary = "진열관리 인기상품 조회")
    public List<ProductManagementSelVo> getProductPop() {
        return service.getProductPop();
    }
    //------------------------------------------상품진열관리 신상품 토글---------------------------------------------
    @PutMapping("/toggleNewProduct")
    @Operation(summary = "진열관리 신상품 토글")
    public ResVo putProductNew(@RequestParam Long iproduct) {
        return service.putProductNew(iproduct);
    }
    //------------------------------------------상품진열관리 인기상품 토글---------------------------------------------
    @PutMapping("/togglePopProduct")
    @Operation(summary = "진열관리 인기상품 토글")
    public ResVo putProductPop(@RequestParam Long iproduct) {
        return service.putProductPop(iproduct);
    }
    //------------------------------------------상품진열관리 추천상품 토글---------------------------------------------
    @PutMapping("/toggleRcProduct")
    @Operation(summary = "진열관리 추천상품 토글")
    public ResVo putProductRc(@RequestParam Long iproduct) {
        return service.putProductRc(iproduct);
    }
    //-----------------------진열관리 상품검색---------------
    @GetMapping("/searchRcProduct")
    @Operation(summary = "진열관리 추천 상품검색")
    public List<AdminProductSearchSelVo> getSearchRcProductSelVo(AdminProductSearchDto dto ) {
        return service.getSearchProductSelVo(dto,pageable(dto.getPage()));
    }
    @GetMapping("/searchPopProduct")
    @Operation(summary = "진열관리 인기 상품검색")
    public List<AdminProductSearchSelVo> getSearchPopProductSelVo(AdminProductSearchDto dto ) {
        return service.getSearchPopProductSelVo(dto,pageable(dto.getPage()));
    }

    @GetMapping("/searchNewProduct")
    @Operation(summary = "진열관리 신상품검색")
    public List<AdminProductSearchSelVo> getSearchNewProductSelVo(AdminProductSearchDto dto) {
        return service.getSearchNewProductSelVo(dto, pageable(dto.getPage()));
    }
    //-----------------------------------------------------------------리뷰검색------------------------------------------------------
    @GetMapping("/searchReview")
    @Operation(summary = "리뷰 검색")
    public List<SearchReviewSelVo> getSearchReview( ReviewSearchDto dto) {
        log.info("pageable(dto.getPage()) = {}", pageable(dto.getPage()));
        return service.getSearchReview(dto,pageable(dto.getPage()));
    }
    //-------------------------------------------------------------숨김리뷰검색------------------------------------------------------
    @GetMapping("/searchHiddenReview")
    @Operation(summary = "숨김리뷰조회")
    public List<SearchReviewSelVo> getHiddenReviewSelVo(ReviewSearchDto dto) {
        return service.getHiddenReview(dto,pageable(dto.getPage()));
    }
    //-------------------------------------------------------------리뷰 관리자 메모 ------------------------------------------------------
    @PatchMapping("/reviewMemo")
    @Operation(summary = "관리자 메모 작성 ")
    public ResVo patchReviewAdminMemo(@RequestBody ReviewMemoInsDto dto) {
        return service.postReviewAdminMemo(dto);
    }
    //----------------------------------------------------------리뷰 숨김 복구 토글*-------------------------------------------------
    @PutMapping("/reviewTogle")
    @Operation(summary = "리뷰 숨김 & 해제")
    public ResVo putReviewTogle(Long ireview) {
        return service.putReviewTogle(ireview);
    }
    //-----------------------------------관리자 리뷰 관리 클릭할때-------------------------
    @GetMapping("/reviewHiCk")
    @Operation(summary = "리뷰 숨김클릭시")
    public List<ReviewHideClickSelVo> getHiCkSelVo (Long ireview) {
        return service.getHiCkSelVo(ireview);
    }
    //--------------------------------------관리자 메모만 ---------------------------------------
    @GetMapping("/reviewMemo")
    @Operation(summary = "리뷰관리자메모")
    public String getReviewMemo(Long ireview) {
        return service.getReviewMeMo(ireview);
    }
    //-----------------------------------------------------------------배너조회------------------------------------------------------
    @GetMapping("/banner")
    @Operation(summary = "배너조회")
    public List<BannerSelVo> getBanner() {
        return service.getBanner();
    }
    //-----------------------------------------------------------------배너등록------------------------------------------------------
    @PostMapping("/banner")
    @Operation(summary = "배너등록")
    public ResVo postBanner(@RequestPart MultipartFile pic, @RequestPart BannerInsDto dto) {
        return service.postBanner(pic, dto);
    }
    //-----------------------------------------------------------------배너수정------------------------------------------------------
    @PatchMapping("/banner")
    @Operation(summary = "배너수정")
    public ResVo updateBanner(@RequestParam Long ibanner, @RequestPart(required = false) MultipartFile pic, @RequestPart BannerInsDto dto) {
        return service.updateBanner(ibanner, pic, dto);
    }
    //-----------------------------------------------------------------배너삭제------------------------------------------------------
    @DeleteMapping("/banner/{ibanner}")
    @Operation(summary = "배너삭제")
    public ResVo delBanner(@PathVariable Long ibanner) {
        return service.delBanner(ibanner);
    }
    @GetMapping("/cancel")
    @Operation(summary = "주문취소통계")
    public List<AdminRefundReturnSelVo> OrderCancelSelVo(AdminRefundReturnDto dto) {
        return service.OrderCancelSelVo(dto);
    }
    @GetMapping("/return")
    @Operation(summary = "주문반품통계")
    public List<AdminRefundReturnSelVo> OrderReturnSelVo(AdminRefundReturnDto dto) {
        return service.OrderReturnSelVo(dto);
    }
}





