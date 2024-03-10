package com.baby.babycareproductsshop.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class BasketIds implements Serializable {

    @Column
    private Long iuser;

    @Column
    private Long iproduct;
}
