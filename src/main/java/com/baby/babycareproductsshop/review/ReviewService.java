package com.baby.babycareproductsshop.review;

import com.baby.babycareproductsshop.common.Const;
import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.CommonErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.review.model.*;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public ResVo insReview(ReviewInsDto dto) {
        //
        dto.setIuser(authenticationFacade.getLoginUserPk());
        ReviewPicsInsDto insDto = new ReviewPicsInsDto();
        insDto.setIreview(dto.getIdetails());
        String target = "/review/" + dto.getIdetails();
        if( dto.getPics() == null) {
            int insReview = mapper.insReview(dto);
            return new ResVo(Const.SUCCESS);
        }
        for (MultipartFile file : dto.getPics()) {
            String savedFileNm = myFileUtils.transferTo(file, target);
            insDto.getPics().add(savedFileNm);
            if(dto.getRepReviewPic() == null) {
                dto.setRepReviewPic(savedFileNm);
            }
        }
        int insReview = mapper.insReview(dto);
        int insReviewPics = mapper.insReviewPics(insDto);
        return new ResVo(Const.SUCCESS);
    }

    public List<ReviewSelVo> getReview(ReviewSelDto dto) {
        //
        dto.setIuser(authenticationFacade.getLoginUserPk());
        List<ReviewSelVo> reviewSelVoList = mapper.getReview(dto);
        List<Integer> iReviewList = new ArrayList<>();
        Map<Integer, ReviewSelVo> reviewMap = new HashMap<>();
        for (ReviewSelVo vo : reviewSelVoList) {
            iReviewList.add(vo.getIreview());
            reviewMap.put(vo.getIreview(), vo);
        }
        //
        if (iReviewList.size() > 0) {
            //
            List<ReviewPicsVo> reviewPicsVoList = mapper.getReviewPics(iReviewList);
            for (ReviewPicsVo vo : reviewPicsVoList) {
                ReviewSelVo reviewSelVo = reviewMap.get(vo.getIreview());
                List<String> pics = reviewSelVo.getPics();
                pics.add(vo.getReviewPic());
            }
        }
        return reviewSelVoList;

    }

    public ResVo delReview(ReviewDelDto dto) {
        //
        dto.setIuser(authenticationFacade.getLoginUserPk());
        int selReview = mapper.selReviewByReview(dto);
        if (selReview == 0) {
            throw new RestApiException(AuthErrorCode.DEL_REVIEW_NOT_FAIL);
        }
        if (selReview == 1) {
            mapper.delReviewByPics(dto);
            mapper.delReview(dto);
        }
        return new ResVo(Const.SUCCESS);
    }
}
