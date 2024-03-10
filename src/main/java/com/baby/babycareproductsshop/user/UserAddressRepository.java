package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.entity.user.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {
}
