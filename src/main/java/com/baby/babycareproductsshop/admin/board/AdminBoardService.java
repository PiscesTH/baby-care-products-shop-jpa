package com.baby.babycareproductsshop.admin.board;

import com.baby.babycareproductsshop.admin.board.model.AdminSelBoardVo;
import com.baby.babycareproductsshop.admin.board.model.AdminSelCommentVo;
import com.baby.babycareproductsshop.board.BoardCommentRepository;
import com.baby.babycareproductsshop.board.BoardRepository;
import com.baby.babycareproductsshop.board.model.AdminInsBoardCommentDto;
import com.baby.babycareproductsshop.entity.board.BoardCommentEntity;
import com.baby.babycareproductsshop.entity.board.BoardEntity;
import com.baby.babycareproductsshop.exception.CommonErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.response.ApiResponse;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import com.baby.babycareproductsshop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminBoardService {
    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public ApiResponse<List<AdminSelBoardVo>> getInquiryList(String keyword){
        long INQUIRY_BOARD_CODE = 3;
        List<BoardEntity> entityList = StringUtils.hasText(keyword) ?
                boardRepository.findAllByBoardCodeAndTitleContainsOrContentsContains(INQUIRY_BOARD_CODE, keyword, keyword)
                : boardRepository.findAllByBoardCode(INQUIRY_BOARD_CODE);
        List<BoardCommentEntity> commentEntityList = boardCommentRepository.findAllByBoardEntityIn(entityList);
        List<AdminSelBoardVo> result = entityList.stream()
                .map(item -> AdminSelBoardVo.builder()
                        .iboard(item.getIboard())
                        .title(item.getTitle())
                        .contents(item.getContents())
                        .responseFl(commentEntityList.stream()
                                .filter(comment -> comment.getBoardEntity().getIboard().equals(item.getIboard()))
                                .toList()
                                .isEmpty() ? 0 : 1)
                        .build())
                .toList();
        return new ApiResponse<>(result);
    }

    @Transactional
    public ApiResponse<AdminSelBoardVo> getInquiry(long iboard) {
        Optional<BoardEntity> optEntity = boardRepository.findById(iboard);
        if (optEntity.isEmpty()) {
            throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
        }
        long iuser = authenticationFacade.getLoginUserPk();
        BoardEntity entity = optEntity.get();
        List<BoardCommentEntity> commentList = boardCommentRepository.findAllByBoardEntity(entity);
        AdminSelBoardVo result = AdminSelBoardVo.builder()
                .iboard(iboard)
                .contents(entity.getContents())
                .title(entity.getTitle())
                .writerNm(entity.getUserEntity().getNm())
                .commentList(commentList.stream()
                        .map(item -> AdminSelCommentVo.builder()
                                .iuser(iuser)
                                .icomment(item.getIcomment())
                                .comment(item.getComment())
                                .build())
                        .toList())
                .build();
        return new ApiResponse<>(result);
    }

    @Transactional
    public ApiResponse<?> postBoardComment(AdminInsBoardCommentDto dto) {
        BoardCommentEntity entity = new BoardCommentEntity();
        entity.setUserEntity(userRepository.getReferenceById((long)authenticationFacade.getLoginUserPk()));
        entity.setComment(dto.getComment());
        entity.setBoardEntity(boardRepository.getReferenceById(dto.getIboard()));
        boardCommentRepository.save(entity);
        return new ApiResponse<>(null);
    }

    public ApiResponse<?> delBoard(long iboard) {
        boardRepository.deleteById(iboard);
        return new ApiResponse<>(null);
    }

    @Transactional
    public ApiResponse<?> delBoardComment(long iboard, long icomment) {
        boardCommentRepository.deleteById(icomment);
        return new ApiResponse<>(null);
    }
}
