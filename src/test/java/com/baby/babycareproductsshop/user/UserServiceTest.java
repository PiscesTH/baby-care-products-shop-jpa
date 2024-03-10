package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.common.AppProperties;
import com.baby.babycareproductsshop.common.MyCookieUtils;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.product.ProductWishListMapper;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import com.baby.babycareproductsshop.security.JwtTokenProvider;
import com.baby.babycareproductsshop.user.model.*;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({UserService.class})
class UserServiceTest {
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private ProductWishListMapper wishListMapper;
    @MockBean
    private UserAddressMapper addressMapper;
    @MockBean
    private UserChildMapper childMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private AppProperties appProperties;
    @MockBean
    private MyCookieUtils myCookieUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private UserService service;

    @Test
    void postSignUp() {
        when(userMapper.selSignInInfoByUid(any())).thenReturn(null);
        when(passwordEncoder.encode(any())).thenReturn("password");
        when(userMapper.insUser(any())).thenReturn(1);
        when(childMapper.insUserChildren(any())).thenReturn(2);
        when(addressMapper.insUserAddress(any())).thenReturn(1);

        UserSignUpDto dto = new UserSignUpDto();
        dto.setChildren(new ArrayList<>());
        ResVo resVo = service.postSignUp(dto);

        verify(userMapper).selSignInInfoByUid(any());
        verify(passwordEncoder).encode(any());
        verify(userMapper).insUser(any());
        verify(addressMapper).insUserAddress(any());
    }

    @Test
    void getClause() {
//        UserClauseVo vo1 = new UserClauseVo();
//        vo1.setIclause(1);
//        vo1.setTitle("test");
//
//        UserClauseVo vo2 = new UserClauseVo();
//        vo2.setIclause(1);
//        vo2.setTitle("test");
//
//        List<UserClauseVo> list = new ArrayList<>();
//        list.add(vo1);
//        list.add(vo2);
//
//        when(userMapper.selClause()).thenReturn(list);
//
//        List<UserClauseVo> result = service.getClause();
//        verify(userMapper).selClause();
//        assertEquals(list, result);
    }

    @Test
    void postCheckUid() {
        when(userMapper.selSignInInfoByUid(any())).thenReturn(null);

        ResVo resVo = service.postCheckUid(new UserCheckUidDto());

        verify(userMapper).selSignInInfoByUid(any());
    }

    @Test
    void postSignIn() {
        when(userMapper.selSignInInfoByUid(any())).thenReturn(new UserSignInProcDto());
        when(passwordEncoder.matches(any(),any())).thenReturn(true);
        when(jwtTokenProvider.generateAccessToken(any())).thenReturn("at");
        when(jwtTokenProvider.generateRefreshToken(any())).thenReturn("rt");
        when(appProperties.getJwt()).thenReturn(new AppProperties.Jwt());

        UserSignInDto dto = new UserSignInDto();
        dto.setUpw("password12!@");
        dto.setUid("uid123");

        UserSignInVo vo = service.postSignIn(any(), dto);

        assertEquals("at", vo.getAccessToken());
    }

    @Test
    void getMyInfo() {
        UserSelMyInfoVo vo = new UserSelMyInfoVo();
        vo.setNm("vam");
        when(userMapper.selMyInfo(23)).thenReturn(vo);
        when(wishListMapper.selWishList(23)).thenReturn(new ArrayList<>());

        UserSelMyInfoVo result = service.getMyInfo();
        assertEquals("vam", result.getNm());

    }

    @Test
    void postCheckUpw() {
        UserSelToModifyVo vo = new UserSelToModifyVo();
        vo.setNm("hubble");
        vo.setUpw("xptmxm123!@#");
        when(userMapper.selUserInfoByIuser(23)).thenReturn(vo);
        when(passwordEncoder.matches(any(),any())).thenReturn(true);
        when(childMapper.selUserChildren(23)).thenReturn(new ArrayList<>());

        UserCheckUpwDto dto = new UserCheckUpwDto();
        dto.setUpw("xptmxm123!@#");

        UserSelToModifyVo result = service.postCheckUpw(dto);
        assertEquals("hubble", result.getNm());
    }

    @Test
    void putUserInfo() {
        UserUpdDto dto = new UserUpdDto();
        dto.setIuser(23);
        dto.setUpw("password");
        dto.setChildren(new ArrayList<>());
        when(passwordEncoder.encode(any())).thenReturn("hashedPw");
        when(childMapper.delUserChildren(23)).thenReturn(1);
        when(childMapper.insUserChildren(any())).thenReturn(1);
        when(userMapper.updUser(any())).thenReturn(1);

        ResVo resVo = service.putUserInfo(dto);

        assertEquals(1, resVo.getResult());
    }

    @Test
    void unregister() {
        when(userMapper.delUser(23)).thenReturn(1);
        ResVo result = service.unregister();

        assertEquals(1, result.getResult());
    }

    @Test
    void postUserAddress() {
        List<UserSelAddressVo> list = new ArrayList<>();
        list.add(new UserSelAddressVo());
        when(addressMapper.insUserAddress(any())).thenReturn(1);

        assertEquals(1, addressMapper.insUserAddress(any()));
    }

    @Test
    void getUserAddress() {
        when(addressMapper.selUserAddress(23)).thenReturn(new ArrayList<>());

        List<UserSelAddressVo> result = service.getUserAddress();
        assertNotNull(result);
    }

    @Test
    void putUserAddress() {
    }

    @Test
    void delUserAddress() {
    }

    @BeforeEach
    void beforeSetting() {
        when(authenticationFacade.getLoginUserPk()).thenReturn(23);
    }
}