package com.baby.babycareproductsshop.admin.user;

import com.baby.babycareproductsshop.admin.user.model.*;
import com.baby.babycareproductsshop.common.*;
import com.baby.babycareproductsshop.entity.order.OrderEntity;
import com.baby.babycareproductsshop.entity.user.UserEntity;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.CommonErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.order.OrderRepository;
import com.baby.babycareproductsshop.response.ApiResponse;
import com.baby.babycareproductsshop.security.JwtTokenProvider;
import com.baby.babycareproductsshop.security.MyPrincipal;
import com.baby.babycareproductsshop.user.UserRepository;
import com.baby.babycareproductsshop.user.model.UserChildDto;
import com.baby.babycareproductsshop.user.model.UserInsAddressDto;
import com.baby.babycareproductsshop.user.model.UserSignInDto;
import com.baby.babycareproductsshop.user.model.UserSignInVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.baby.babycareproductsshop.common.Const.rtName;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final MyCookieUtils myCookieUtils;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;
    private final AdminUserRepository adminUserRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public ApiResponse<UserSignInVo> postSigninAdmin(HttpServletResponse res, UserSignInDto dto) {
        Optional<UserEntity> optEntity = userRepository.findByProviderTypeAndUid(ProviderTypeEnum.LOCAL, dto.getUid());
        UserEntity entity = optEntity.orElseThrow(() -> new RestApiException(AuthErrorCode.LOGIN_FAIL));
        if (!RoleEnum.ADMIN.equals(entity.getRole())) {
            throw new RestApiException(AuthErrorCode.UNAUTHORIZED_USER);
        }
        if (!passwordEncoder.matches(dto.getUpw(), entity.getUpw())) {
            throw new RestApiException(AuthErrorCode.LOGIN_FAIL);
        }
        MyPrincipal myPrincipal = MyPrincipal.builder()
                .iuser(entity.getIuser().intValue())
                .build();
        myPrincipal.getRoles().add(entity.getRole().name());

        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        String rt = jwtTokenProvider.generateRefreshToken(myPrincipal);

        int rtCookieMaxAge = appProperties.getJwt().getRefreshCookieMaxAge();
        myCookieUtils.deleteCookie(res, rtName);
        myCookieUtils.setCookie(res, rtName, rt, rtCookieMaxAge);
        log.info("rt : {}", rt);

        String iuser = String.valueOf(entity.getIuser());
        redisTemplate.opsForValue().set(iuser, rt, 1296000, TimeUnit.SECONDS);

        String redisRt = redisTemplate.opsForValue().get(iuser);
        log.info("redisRt : {}", redisRt);
        UserSignInVo result = UserSignInVo.builder()
                .result(Const.SUCCESS)
                .nm(entity.getNm())
                .accessToken(at)
                .build();
        return new ApiResponse<>(result);
    }

    public ApiResponse<List<AdminSelAllUserVo>> getUserList(AdminSelAllUserDto dto, Pageable pageable) {
        List<UserEntity> entityList = adminUserRepository.selUserAll(dto, pageable);
        log.info("userEntity : {}", entityList);
        long totalCnt = adminUserRepository.selUserAllCount(dto).getTotalCnt();
        List<AdminSelAllUserVo> result = entityList.stream().filter(item -> item.getIuser() != 1)
                .map(item -> AdminSelAllUserVo.builder()
                        .nm(item.getNm())
                        .iuser(item.getIuser())
                        .email(item.getEmail())
                        .phoneNumber(item.getPhoneNumber())
                        .registeredAt(dto.getUnregisteredFl() == 0 ? item.getCreatedAt() : null)
                        .unregisteredAt(dto.getUnregisteredFl() == 1 ? item.getUpdatedAt() : null)
                        .totalCnt(totalCnt)
                        .build()
                ).toList();
        return new ApiResponse<>(result);
    }

    @Transactional
    public ApiResponse<AdminSelUserVo> getUserInfo(long iuser) {
        Optional<UserEntity> optEntity = userRepository.findById(iuser);
        if (optEntity.isEmpty()) {
            throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
        }
        UserEntity entity = optEntity.get();
        AdminSelUserVo result = AdminSelUserVo.builder()
                .nm(entity.getNm())
                .uid(entity.getUid())
                .registeredAt(entity.getCreatedAt())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .adminMemo(entity.getAdminMemo())
                .addresses(entity.getAddressEntityList().stream().map(item -> UserInsAddressDto.builder()
                        .zipCode(item.getZipCode())
                        .address(item.getAddress())
                        .addressDetail(item.getAddressDetail())
                        .build()).toList())
                .children(entity.getChildEntityList().stream().map(item -> UserChildDto.builder()
                        .ichildAge(item.getUserChildAgeEntity().getIchildAge().intValue())
                        .gender(item.getGender())
                        .build()).toList())
                .build();
        return new ApiResponse<>(result);
    }

    @Transactional
    public ApiResponse<?> patchUserInfo(long iuser, AdminUpdUserDto dto) {
        UserEntity entity = userRepository.getReferenceById(iuser);
        if (StringUtils.hasText(dto.getUpw())) {
            String hashedUpw = passwordEncoder.encode(dto.getUpw());
            entity.setUpw(hashedUpw);
        }
        entity.setAdminMemo(dto.getAdminMemo());
        return new ApiResponse<>(null);
    }

    public ApiResponse<List<AdminSelUserSignupVo>> getUserSignupStatistics(AdminSelUserSignupDto dto) {
        List<UserEntity> entityList = adminUserRepository.selUserSignupStatistics(dto);
        Map<String, AdminSelUserSignupVo> map = new HashMap<>();
        int totalRegisterCnt = 0;
        for (UserEntity entity : entityList) {
            totalRegisterCnt += entity.getIuser();
        }

        List<AdminSelUserSignupVo> result = new ArrayList<>();
        for (UserEntity entity : entityList) {
            AdminSelUserSignupVo vo = new AdminSelUserSignupVo();
            vo.setRegisterCnt(entity.getIuser().intValue());
            vo.setCreatedAt(entity.getCreatedAt());
            vo.setDate(Utils.getDate(dto.getYear(), dto.getMonth(), vo));
            vo.setTotalRegisterCnt(totalRegisterCnt);
            vo.setRegisterRate(String.format("%.2f", (double) vo.getRegisterCnt() / totalRegisterCnt));
            map.put(Utils.getDate(dto.getYear(), dto.getMonth(), vo), vo);
            result.add(vo);
        }
        if (dto.getYear() == 0 && dto.getMonth() == 0) {
            return new ApiResponse<>(result);
        }
        int date = Utils.getDaysOrMonths(dto.getYear(), dto.getMonth());
        for (int i = 1; i <= date; i++) {
            String key = Utils.getKey(dto.getYear(), dto.getMonth(), i);
            AdminSelUserSignupVo vo = map.get(key);
            if (vo == null) {
                map.put(key, new AdminSelUserSignupVo(key));
            }
        }
        result = map.values().stream().sorted().toList();
        return new ApiResponse<>(result);
    }

    @Transactional
    public ApiResponse<ResVo> changeRegistrationStatus(long iuser) {
        UserEntity userEntity = userRepository.getReferenceById(iuser);
        userEntity.setUnregisterFl(1 - userEntity.getUnregisterFl());
        return new ApiResponse<>(new ResVo(userEntity.getUnregisterFl().intValue()));
    }
}
