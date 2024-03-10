package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.CharEncodingConfig;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.security.JwtAuthenticationFilter;
import com.baby.babycareproductsshop.user.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(CharEncodingConfig.class)
@WebMvcTest(controllers = UserController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@WithMockUser
class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService service;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void getClause() throws Exception {
//        UserClauseVo vo1 = new UserClauseVo();
//        UserClauseVo vo2 = new UserClauseVo();
//        vo1.setIclause(1);
//        vo2.setIclause(2);
//        List<UserClauseVo> result = new ArrayList<>();
//        result.add(vo1);
//        result.add(vo2);
//        given(service.getClause()).willReturn(result);
//
//        mvc.perform(
//                        MockMvcRequestBuilders.get("/api/user/sign-up")
//                                .with(csrf())
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().string(mapper.writeValueAsString(result)))
//                .andDo(print());
//
//        verify(service).getClause();
    }

    @Test
    void postSignUp() throws Exception {
        ResVo result = new ResVo(1);
        UserSignUpDto dto = new UserSignUpDto();
        dto.setUid("test123");
        dto.setUpw("test123!@#");
        dto.setNm("test");
        dto.setEmail("test@naver.com");
        dto.setZipCode("12345");
        dto.setAddress("korea");
        dto.setAddressDetail("Seoul");
        dto.setPhoneNumber("010-1111-2222");
        dto.setIuser(1);
        List<UserChildDto> children = new ArrayList<>();
        children.add(UserChildDto.builder()
                .iuser(1)
                .gender("M")
                .ichildAge(1)
                .build());
        dto.setChildren(children);

        given(service.postSignUp(any())).willReturn(result);

        mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).postSignUp(any());
    }

    @Test
    void postCheckUid() throws Exception {
        ResVo result = new ResVo(1);
        UserCheckUidDto dto = new UserCheckUidDto();
        dto.setUid("test123");
        given(service.postCheckUid(any())).willReturn(result);

        mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/sign-up/check-id")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).postCheckUid(any());
    }

    @Test
    void postSignIn() throws Exception {
        UserSignInDto dto = new UserSignInDto();
        dto.setUid("test123");
        dto.setUpw("test123!@#");
        UserSignInVo result = UserSignInVo.builder()
                .result(1)
                .build();
        given(service.postSignIn(any(), any())).willReturn(result);

        mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/sign-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).postSignIn(any(), any());
    }

    @Test
    void getMyInfo() throws Exception {
        UserSelMyInfoVo result = new UserSelMyInfoVo();
        result.setNm("test");
        given(service.getMyInfo()).willReturn(result);

        mvc.perform(
                        MockMvcRequestBuilders.get("/api/user/my-page")
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).getMyInfo();
    }

    @Test
    void postCheckUpw() throws Exception {
        UserSelToModifyVo result = new UserSelToModifyVo();
        result.setNm("test");
        UserCheckUpwDto dto = new UserCheckUpwDto();
        dto.setUpw("test123!@#");

        given(service.postCheckUpw(any())).willReturn(result);

        mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/modify")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).postCheckUpw(any());
    }

    @Test
    void putUserInfo() throws Exception {
        UserUpdDto dto = new UserUpdDto();
        dto.setUpw("test123!@#");
        dto.setNm("test");
        dto.setEmail("test@naver.com");
        dto.setPhoneNumber("010-1111-2222");
        dto.setIuser(1);
        List<UserChildDto> children = new ArrayList<>();
        children.add(UserChildDto.builder()
                .iuser(1)
                .gender("M")
                .ichildAge(1)
                .build());
        dto.setChildren(children);

        given(service.putUserInfo(any())).willReturn(new ResVo(1));

        mvc.perform(
                        MockMvcRequestBuilders.put("/api/user/modify")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(new ResVo(1))))
                .andDo(print());

        verify(service).putUserInfo(any());
    }

    @Test
    void unregister() throws Exception {
        given(service.unregister()).willReturn(new ResVo(1));

        mvc.perform(
                        MockMvcRequestBuilders.delete("/api/user/modify")
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(new ResVo(1))))
                .andDo(print());

        verify(service).unregister();
    }

    @Test
    void postSignout() throws Exception{
        given(service.signout(any())).willReturn(new ResVo(1));

        mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/signout")
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(new ResVo(1))))
                .andDo(print());

        verify(service).signout(any());
    }

    @Test
    void getRefreshToken() throws Exception {
        UserSignInVo result = UserSignInVo.builder()
                .accessToken("at")
                .build();
        given(service.getRefreshToken(any())).willReturn(result);

        mvc.perform(
                        MockMvcRequestBuilders.get("/api/user/refresh-token")
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).getRefreshToken(any());
    }
}