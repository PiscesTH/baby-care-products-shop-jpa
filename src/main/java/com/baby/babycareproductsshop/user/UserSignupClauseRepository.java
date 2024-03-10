package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.entity.user.UserSignupClauseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSignupClauseRepository extends JpaRepository<UserSignupClauseEntity, Long> {
    List<UserSignupClauseEntity> findAllByRequired(String required);
}
