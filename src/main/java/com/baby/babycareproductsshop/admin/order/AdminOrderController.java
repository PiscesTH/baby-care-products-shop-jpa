package com.baby.babycareproductsshop.admin.order;

import com.baby.babycareproductsshop.admin.order.model.*;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.exception.CommonErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.baby.babycareproductsshop.common.Const.PAGE_SIZE;

@Slf4j
@RestController
@RequestMapping("/api/admin/order")
@RequiredArgsConstructor
@Tag(name = "관리자 기능 - 주문 관리 API❤️")
public class AdminOrderController {
    private final AdminOrderService service;
    private final AdminOrderCommonValid valid;

    public Pageable pageable(int page) {
        return PageRequest.of(page, PAGE_SIZE);
    }

    @Operation(summary = "관리자 메모 수정", description = "<ul><strong>iorder - 주문 번호(PK)</strong></ul> <ul><strong>adminMemo - 관리자 메모</strong><br></ul>")
    @PatchMapping("/memo")
    public ResVo updAdminMemo(@RequestBody OrderMemoUpdDto dto) {
        return service.updAdminMemo(dto);
    }

    @PutMapping
    @Operation(summary = "주문 일괄 처리",
            description = """
                    <ul><strong>iorders - 주문 번호(PK)</strong></ul>
                    <ul><strong>processState - 주문 처리 상태</strong><br>
                    <li>전체 - 0</li>
                    <li>입금 대기 - 1</li>
                    <li>배송 준비중 - 2</li>
                    <li>배송중 - 3</li>
                    <li>배송완료 - 4</li>
                    <li>취소 - 5</li>
                    <li>반품 - 6</li></ul>""")
    public ResVo updOrderStateProcess(@RequestBody OrderBatchProcessDto dto) {
        return service.updOrderStateProcess(dto);
    }

    @GetMapping
    @Operation(summary = "주문 전체 리스트 출력", description = """
            <ul><strong>searchCategory</strong><br>
            <li>전체 - 0</li>
            <li>주문 번호(iorder) - 1</li>
            <li>상품 번호(일련 번호, iproduct) - 2</li>
            <li>회원 아이디 - 3</li>
            <li>주문자명 - 4</li>
            <li>입금자명 - 5</li>
            <li>수령자명 - 6</li>
            <li>수령자 핸드폰 번호 - 7</li></ul>
            <ul><strong>keyword - 검색 키워드</strong></ul>
            <ul><strong>startDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>endDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>dateFl - 기간 검색</strong><br>
            <li>오늘 - 0</li>
            <li>어제 - 1</li>
            <li>일주일 - 2</li>
            <li>지난 달 - 3</li>
            <li>1개월 - 4</li>
            <li>3개월 - 5</li>
            <li>전체 - 6</li></ul>
            <ul><strong>payCategory</strong><br>
            <li>전체 - 0</li>
            <li>무통장 입금 - 1</li>
            <li>신용카드 - 2</li></ul>
            <ul><strong>processState</strong><br>
            <li>전체 - 0</li>
            <li>입금 대기 - 1</li>
            <li>배송 준비중 - 2</li>
            <li>배송중 - 3</li>
            <li>배송완료 - 4</li></ul>
            <ul><strong>sort</strong><br>
            <li>주문일 역순 - 0</li>
            <li>주문일 순 - 1</li>
            <li>처리일 역순 - 2</li>
            <li>처리일 순 - 3</li></ul>""")
    public List<OrderListVo> getOrderList(OrderFilterDto dto) {
        OrderFilterDto orderFilterDto = valid.commonValid(dto);
        return service.getOrderList(orderFilterDto, pageable(dto.getPage()));
    }

    @GetMapping("/user/{iuser}")
    @Operation(summary = "회원 주문 리스트 출력", description = """
            <ul><strong><font color="#D1180B">2024-03-01 추가</font><br></ul>
            <ul><strong>iuser - 회원 PK</strong><br></ul>
            <ul><strong>dateFl - 기간 검색</strong><br>
            <li>오늘 - 0</li>
            <li>어제 - 1</li>
            <li>일주일 - 2</li>
            <li>지난 달 - 3</li>
            <li>1개월 - 4</li>
            <li>3개월 - 5</li>
            <li>전체 - 6</li></ul>
            <ul><strong>startDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>endDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>processState</strong><br>
            <li>전체 - 0</li>
            <li>입금 대기 - 1</li>
            <li>배송 준비중 - 2</li>
            <li>배송중 - 3</li>
            <li>배송완료 - 4</li>
            <li>취소 - 5</li>
            <li>반품 - 6</li></ul>
            <ul><strong>sort</strong><br>
            <li>주문일 역순 - 0(default)</li>
            <li>주문일 순 - 1</li>
            <li>처리일 역순 - 2</li>
            <li>처리일 순 - 3</li></ul>""")
    public List<OrderListVo> getUserOrderList(@PathVariable(name = "iuser") long iuser, OrderUserFilterDto dto, @Parameter(hidden = true) @PageableDefault(size = 2) Pageable pageable) {
        dto.setIuser(iuser);
        OrderUserFilterDto orderUserFilterDto = valid.commonValid(dto);
        return service.getUserOrderList(orderUserFilterDto, pageable);
    }

    @GetMapping("/details")
    @Operation(summary = "주문 상세 리스트 출력", description = """
            <ul><strong>searchCategory</strong><br>
            <li>전체 - 0</li>
            <li>주문 번호(iorder) - 1</li>
            <li>상품 번호(일련 번호, iproduct) - 2</li>
            <li>회원 아이디 - 3</li>
            <li>주문자명 - 4</li>
            <li>입금자명 - 5</li>
            <li>수령자명 - 6</li>
            <li>수령자 핸드폰 번호 - 7</li></ul>
            <ul><strong>keyword - 검색 키워드</strong></ul>
            <ul><strong>startDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>endDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>dateFl - 기간 검색</strong><br>
            <li>전체 - 0</li>
            <li>오늘 - 1</li>
            <li>어제 - 2</li>
            <li>일주일 - 3</li>
            <li>지난 달, 1개월 - 4, 5</li>
            <li>3개월 - 6</li></ul>
            <ul><strong>payCategory - 결제 수단</strong><br>
            <li>전체 - 0</li>
            <li>무통장 입금 - 1</li>
            <li>신용카드 - 2</li></ul>
            <ul><strong>sort</strong><br>
            <li>주문일 역순 - 0</li>
            <li>주문일 순 - 1</li>
            <li>처리일 역순 - 2</li>
            <li>처리일 순 - 3</li></ul>""")
    @Parameters(value = {@Parameter(name = "processState", description = "주문 처리 상태<br>전체 - 0<br>입금 대기 - 1<br>배송 준비중 - 2<br>배송중 - 3<br>배송완료 - 4<br>")})
    public List<OrderDetailsListVo> getOrderDetailsList(@RequestParam(name = "processState") int processState, OrderSmallFilterDto dto) {
        dto.setProcessState(processState);
        OrderSmallFilterDto orderSmallFilterDto = valid.commonValid(dto);
        return service.getOrderDetailsList(orderSmallFilterDto, pageable(dto.getPage()));
    }

    @GetMapping("/delete")
    @Operation(summary = "주문 취소 리스트 출력", description = """
            <ul><strong>searchCategory</strong><br>
            <li>전체 - 0</li>
            <li>주문 번호(iorder) - 1</li>
            <li>상품 번호(일련 번호, iproduct) - 2</li>
            <li>회원 아이디 - 3</li>
            <li>주문자명 - 4</li>
            <li>입금자명 - 5</li>
            <li>수령자명 - 6</li>
            <li>수령자 핸드폰 번호 - 7</li></ul>
            <ul><strong>keyword - 검색 키워드</strong></ul>
            <ul><strong>startDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>endDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>dateFl - 기간 검색</strong><br>
            <li>오늘 - 0</li>
            <li>어제 - 1</li>
            <li>일주일 - 2</li>
            <li>지난 달 - 3</li>
            <li>1개월 - 4</li>
            <li>3개월 - 5</li>
            <li>전체 - 6</li></ul>
            <ul><strong>payCategory</strong><br>
            <li>전체 - 0</li>
            <li>무통장 입금 - 1</li>
            <li>신용카드 - 2</li></ul>
            <ul><strong>sort</strong><br>
            <li>주문일 역순 - 0</li>
            <li>주문일 순 - 1</li>
            <li>처리일 역순 - 2</li>
            <li>처리일 순 - 3</li></ul>""")
    public List<OrderDeleteVo> getOrderDeleteList(OrderSmallFilterDto dto) {
        OrderSmallFilterDto orderSmallFilterDto = valid.commonValid(dto);
        return service.getOrderDeleteList(orderSmallFilterDto, pageable(dto.getPage()));
    }

    @GetMapping("/refund")
    @Operation(summary = "주문 반품 리스트 출력", description = """
            <ul><strong>searchCategory</strong><br>
            <li>전체 - 0</li>
            <li>주문 번호(iorder) - 1</li>
            <li>상품 번호(일련 번호, iproduct) - 2</li>
            <li>회원 아이디 - 3</li>
            <li>주문자명 - 4</li>
            <li>입금자명 - 5</li>
            <li>수령자명 - 6</li>
            <li>수령자 핸드폰 번호 - 7</li></ul>
            <ul><strong>keyword - 검색 키워드</strong></ul>
            <ul><strong>startDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>endDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>dateFl - 기간 검색</strong><br>
            <li>오늘 - 0</li>
            <li>어제 - 1</li>
            <li>일주일 - 2</li>
            <li>지난 달 - 3</li>
            <li>1개월 - 4</li>
            <li>3개월 - 5</li>
            <li>전체 - 6</li></ul>
            <ul><strong>payCategory</strong><br>
            <li>전체 - 0</li>
            <li>무통장 입금 - 1</li>
            <li>신용카드 - 2</li></ul>
            <ul><strong>sort</strong><br>
            <li>주문일 역순 - 0</li>
            <li>주문일 순 - 1</li>
            <li>처리일 역순 - 2</li>
            <li>처리일 순 - 3</li></ul>""")
    public List<OrderRefundListVo> getOrderRefundList(OrderSmallFilterDto dto) {
        OrderSmallFilterDto orderSmallFilterDto = valid.commonValid(dto);
        return service.getOrderRefundList(orderSmallFilterDto, pageable(dto.getPage()));
    }

    @GetMapping("/memo")
    @Operation(summary = "주문 리스트 관리자 메모 출력", description = """
            <ul><strong>searchCategory</strong><br>
            <li>전체 - 0</li>
            <li>주문 번호(iorder) - 1</li>
            <li>상품 번호(일련 번호, iproduct) - 2</li>
            <li>회원 아이디 - 3</li>
            <li>주문자명 - 4</li>
            <li>입금자명 - 5</li>
            <li>수령자명 - 6</li>
            <li>수령자 핸드폰 번호 - 7</li></ul>
            <ul><strong>keyword - 검색 키워드</strong></ul>
            <ul><strong>startDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>endDate - (예) 2024-02-22</strong><br></ul>
            <ul><strong>dateFl - 기간 검색</strong><br>
            <li>오늘 - 0</li>
            <li>어제 - 1</li>
            <li>일주일 - 2</li>
            <li>지난 달 - 3</li>
            <li>1개월 - 4</li>
            <li>3개월 - 5</li>
            <li>전체 - 6</li></ul>
            <ul><strong>sort</strong><br>
            <li>주문일 역순 - 0</li>
            <li>주문일 순 - 1</li>
            <li>처리일 역순 - 2</li>
            <li>처리일 순 - 3</li></ul>""")
    public List<OrderMemoListVo> getOrderAdminMemoList(OrderMemoListDto dto) {
        OrderMemoListDto orderMemoListDto = valid.commonValid(dto);
        return service.getOrderAdminMemoList(orderMemoListDto, pageable(dto.getPage()));
    }

    @Operation(summary = "주문 상세 페이지 출력")
    @Parameters(value = {@Parameter(name = "iorder", description = "주문 PK")})
    @GetMapping("/details/{iorder}")
    public List<OrderDetailsVo> getOrderDetails(@PathVariable(name = "iorder") int iorder) {
        return service.getOrderDetails(iorder);
    }

    //------------------------------------------th
    @Operation(summary = "매출 및 주문 통계 조회", description = """
            type = sales : 매출 통계<br>
            type = orderCnt : 주문 통계<br>
            <br>
            year & month = 0 : 연도별<br>
            year != 0 & month = 0 : 월별<br>
            year & month  != 0: 일별
            """)
    @GetMapping("/{type}")
    public ApiResponse<?> getOrderStatistics(@PathVariable String type, AdminSelOrderStatisticsDto dto) {
        log.info(type);
        return switch (type) {
            case "sales" -> service.getSalesStatistics(dto);
            case "orderCnt" -> service.getOrderCntStatistics(dto);
            default ->  throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
        };
    }
}