package com.baby.babycareproductsshop.entity.user;

import com.baby.babycareproductsshop.entity.CreatedAtEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "t_user_child")
public class UserChildEntity extends CreatedAtEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ichild;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "iuser", referencedColumnName = "iuser", columnDefinition = "BIGINT UNSIGNED")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @Column(columnDefinition = "CHAR(1) CHECK(gender IN ('W', 'M'))")
    private String gender;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ichild_age", columnDefinition = "BIGINT UNSIGNED")
    private UserChildAgeEntity userChildAgeEntity;
}
