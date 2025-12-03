package com.smushytaco.car_shop.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import manifold.ext.props.rt.api.var;

@Entity
@DiscriminatorValue("2")
public class OutsourcedPart extends Part {
    public OutsourcedPart(final String name, final double price, final int inv, final int minInv, final int maxInv, final String companyName) {
        super(name, price, inv, minInv, maxInv);
        this.companyName = companyName;
    }
    public OutsourcedPart() {}
    @NotBlank(message = "Company Name cannot be blank.")
    @var
    String companyName;
}