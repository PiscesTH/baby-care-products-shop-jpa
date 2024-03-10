package com.baby.babycareproductsshop.admin.category;

import com.baby.babycareproductsshop.admin.category.model.CategoryVo;
import com.baby.babycareproductsshop.admin.category.model.MainCategoryUpdDto;
import com.baby.babycareproductsshop.admin.category.model.MiddleCategoryInsDto;
import com.baby.babycareproductsshop.admin.category.model.MiddleCategoryUpdDto;
import com.baby.babycareproductsshop.common.Const;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.entity.product.ProductMainCategoryEntity;
import com.baby.babycareproductsshop.entity.product.ProductMiddleCategoryEntity;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminCategoryService {
    private final AdminMainCategoryRepository mainRepository;
    private final AdminMiddleCategoryRepository middleRepository;

    public List<CategoryVo> getCategoryList() {
        return mainRepository.findAll()
                .stream()
                .map(item -> {
                    List<CategoryVo.MiddleCategoryVo> middleCategory = middleRepository.getByProductMainCategory_Imain(item.getImain())
                            .stream()
                            .map(middle -> CategoryVo.MiddleCategoryVo
                                    .builder()
                                    .candidateKey(middle.getCandidateKey())
                                    .imiddle(middle.getImiddle())
                                    .middleCategory(middle.getMiddleCategory())
                                    .build())
                            .toList();

                    return CategoryVo
                            .builder()
                            .imain(item.getImain())
                            .mainCategory(item.getMainCategory())
                            .middleCategoryList(middleCategory)
                            .build();
                })
                .toList();
    }

    @Transactional
    public ResVo updMainCategory(MainCategoryUpdDto dto) {
        try {
            ProductMainCategoryEntity mainCategoryEntity = mainRepository.getReferenceById(dto.getImain());
            mainCategoryEntity.setImain(dto.getImain());
            mainCategoryEntity.setMainCategory(dto.getMainCategory());
            mainRepository.save(mainCategoryEntity);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.CATEGORY_UPDATE_FAIL);
        }
    }

    @Transactional
    public ResVo updMiddleCategory(MiddleCategoryUpdDto dto) {
        try {
            ProductMiddleCategoryEntity middleCategoryEntity = middleRepository.getReferenceById(dto.getCandidateKey());
            middleCategoryEntity.setCandidateKey(dto.getCandidateKey());
            middleCategoryEntity.setMiddleCategory(dto.getMiddleCategory());
            middleRepository.save(middleCategoryEntity);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.CATEGORY_UPDATE_FAIL);
        }
    }

    @Transactional
    public ResVo insMainCategory(String mainCategory) {
        try {
            ProductMainCategoryEntity mainCategoryEntity = new ProductMainCategoryEntity();
            mainCategoryEntity.setMainCategory(mainCategory);
            mainRepository.save(mainCategoryEntity);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.CATEGORY_INSERT_FAIL);
        }
    }

    @Transactional
    public ResVo insMiddleCategory(MiddleCategoryInsDto dto) {
        try {
            ProductMiddleCategoryEntity middleCategoryEntity = new ProductMiddleCategoryEntity();
            long imiddle = middleRepository.getMiddleCategoryMaxNumber(dto.getImain()) + 1;
            middleCategoryEntity.setImiddle(imiddle);
            middleCategoryEntity.setMiddleCategory(dto.getMiddleCategory());
            middleCategoryEntity.setProductMainCategory(mainRepository.getReferenceById(dto.getImain()));
            middleRepository.save(middleCategoryEntity);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.CATEGORY_INSERT_FAIL);
        }
    }

    @Transactional
    public ResVo delMainCategory(long imain) {
        try {
            ProductMainCategoryEntity mainCategoryEntity = mainRepository.getReferenceById(imain);
            mainRepository.delete(mainCategoryEntity);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.CATEGORY_DELETE_FAIL);
        }
    }

    public ResVo delMiddleCategory(long candidateKey) {
        try {
            ProductMiddleCategoryEntity middleCategoryEntity = middleRepository.getReferenceById(candidateKey);
            middleRepository.delete(middleCategoryEntity);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.CATEGORY_DELETE_FAIL);
        }
    }
}
