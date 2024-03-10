package com.baby.babycareproductsshop.entity.user;

import com.baby.babycareproductsshop.entity.CreatedAtEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "t_child_age")
public class UserChildAgeEntity extends CreatedAtEntity {
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ichildAge;

    @NotNull
    @Column(length = 20)
    private String childAge;
}
