package com.baby.babycareproductsshop.entity.user;

import com.baby.babycareproductsshop.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "t_signup_clause")
public class UserSignupClauseEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iclause;

    @NotNull
    @Column(length = 50)
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(columnDefinition = "CHAR(2) CHECK(required IN ('Y', 'N'))")
    private String required;
}
