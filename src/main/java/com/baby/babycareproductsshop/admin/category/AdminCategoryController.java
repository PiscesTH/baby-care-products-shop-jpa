package com.baby.babycareproductsshop.admin.category;

import com.baby.babycareproductsshop.admin.category.model.CategoryVo;
import com.baby.babycareproductsshop.admin.category.model.MainCategoryUpdDto;
import com.baby.babycareproductsshop.admin.category.model.MiddleCategoryInsDto;
import com.baby.babycareproductsshop.admin.category.model.MiddleCategoryUpdDto;
import com.baby.babycareproductsshop.common.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/category")
@Tag(name = "관리자 기능 - 카테고리 API❤️")
public class AdminCategoryController {
    private final AdminCategoryService service;

    @Operation(summary = "카테고리 출력")
    @GetMapping
    public List<CategoryVo> getCategoryList() {
        return service.getCategoryList();
    }

    @Operation(summary = "1차 카테고리 등록")
    @PostMapping("/main")
    @Parameters(value = {@Parameter(name = "main_category", description = "1차 카테고리명")})
    public ResVo insMainCategory(@RequestParam(name = "main_category") String mainCategory) {
        return service.insMainCategory(mainCategory);
    }

    @Operation(summary = "2차 카테고리 등록")
    @PostMapping("/middle")
    public ResVo insMiddleCategory(@RequestBody MiddleCategoryInsDto dto) {
        return service.insMiddleCategory(dto);
    }

    @Operation(summary = "1차 카테고리 수정")
    @PatchMapping("/main")
    public ResVo updMainCategory(@RequestBody MainCategoryUpdDto dto) {
        return service.updMainCategory(dto);
    }

    @Operation(summary = "2차 카테고리 수정")
    @PatchMapping("/middle")
    public ResVo updMiddleCategory(@RequestBody MiddleCategoryUpdDto dto) {
        return service.updMiddleCategory(dto);
    }

    @Operation(summary = "1차 카테고리 삭제")
    @DeleteMapping("/main/{imain}")
    @Parameters(value = {@Parameter(name = "imain", description = "1차 카테고리 PK")})
    public ResVo delMainCategory(@PathVariable(name = "imain") int imain) {
        return service.delMainCategory(imain);
    }

    @Operation(summary = "2차 카테고리 삭제")
    @DeleteMapping("/middle/{candidateKey}")
    @Parameters(value = {@Parameter(name = "candidateKey", description = "2차 카테고리 PK")})
    public ResVo delMiddleCategory(@PathVariable(name = "candidateKey") int candidateKey) {
        return service.delMiddleCategory(candidateKey);
    }
}
