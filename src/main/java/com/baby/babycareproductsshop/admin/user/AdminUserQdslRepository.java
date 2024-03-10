package com.baby.babycareproductsshop.admin.user;

import com.baby.babycareproductsshop.admin.user.model.AdminSelAllUserDto;
import com.baby.babycareproductsshop.admin.user.model.AdminSelAllUserVo;
import com.baby.babycareproductsshop.admin.user.model.AdminSelUserSignupDto;
import com.baby.babycareproductsshop.entity.user.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUserQdslRepository {
    List<UserEntity> selUserAll(AdminSelAllUserDto dto, Pageable pageable);
    List<UserEntity> selUserSignupStatistics(AdminSelUserSignupDto dto);
    AdminSelAllUserVo selUserAllCount(AdminSelAllUserDto dto);
}
