package com.baby.babycareproductsshop.entity.user;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class UserAgreementsIds implements Serializable {
    private Long iclause;
    private Long iuser;
}
