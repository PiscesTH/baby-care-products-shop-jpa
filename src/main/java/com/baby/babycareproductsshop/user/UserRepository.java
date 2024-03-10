package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.common.ProviderTypeEnum;
import com.baby.babycareproductsshop.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByProviderTypeAndUid(ProviderTypeEnum providerType, String uid);
    List<UserEntity> findAllByUnregisterFlAndUpdatedAtLessThan(Long unregisterFl, LocalDateTime dateTime);
    Optional<UserEntity> findByUid(String uid);
}
