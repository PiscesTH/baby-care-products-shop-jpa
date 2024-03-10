package com.baby.babycareproductsshop.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.baby.babycareproductsshop.common.Const.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInsAddressDto {
    @JsonIgnore
    private int iuser;

    @Schema(title = "우편 번호")
    @NotBlank(message = ZIP_CODE_IS_BLANK)
    private String zipCode;

    @Schema(title = "주소")
    @NotBlank(message = ADDRESS_IS_BLANK)
    private String address;

    @Schema(title = "상세 주소")
    @NotBlank(message = ADDRESS_DETAIL_IS_BLANK)
    private String addressDetail;

    public UserInsAddressDto(UserSignUpDto dto) {
        this.iuser = dto.getIuser();
        this.zipCode = dto.getZipCode();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
    }
}
