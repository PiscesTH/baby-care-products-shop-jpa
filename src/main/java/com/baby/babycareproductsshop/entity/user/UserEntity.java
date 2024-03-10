package com.baby.babycareproductsshop.entity.user;

import com.baby.babycareproductsshop.common.ProviderTypeEnum;
import com.baby.babycareproductsshop.common.RoleEnum;
import com.baby.babycareproductsshop.entity.BaseEntity;
import com.baby.babycareproductsshop.entity.review.ReviewEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_user", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"uid", "provider_type"}
        )
})
public class UserEntity extends BaseEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iuser;

    @NotNull
    @Column(length = 100)
    private String uid;

    @NotNull
    @Column(length = 2100)
    private String upw;

    @NotNull
    @Column(length = 20)
    private String nm;

    @NotNull
    @Column(columnDefinition = "CHAR(13)")
    private String phoneNumber;

    @NotNull
    @Column(length = 50)
    private String email;

    @Column(length = 50)
    @ColumnDefault("'0'")
    private Long unregisterFl;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column
    @ColumnDefault("'USER'")
    private RoleEnum role;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column
    @ColumnDefault("'LOCAL'")
    private ProviderTypeEnum providerType;

    @Column(length = 100)
    private String adminMemo;

    @ToString.Exclude
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
    private List<UserAddressEntity> addressEntityList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.PERSIST)
    private List<UserChildEntity> childEntityList = new ArrayList<>();
}
