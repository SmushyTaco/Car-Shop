package com.smushytaco.car_shop.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("1")
public class InHousePart extends Part {
    public InHousePart(final String name, final double price, final int inv, final int minInv, final int maxInv, final int partId) {
        super(name, price, inv, minInv, maxInv);
        this.partId = partId;
    }
    private int partId;
}