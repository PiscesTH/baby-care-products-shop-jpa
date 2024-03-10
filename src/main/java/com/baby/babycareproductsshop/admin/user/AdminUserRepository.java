package com.baby.babycareproductsshop.admin.user;

import com.baby.babycareproductsshop.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<UserEntity, Long>, AdminUserQdslRepository {

}
