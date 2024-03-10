package com.baby.babycareproductsshop.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPrincipal {  //토큰에 넣을 때 사용하는 용도 ?
    private int iuser;
    @Builder.Default
    private List<String> roles = new ArrayList<>();
}
