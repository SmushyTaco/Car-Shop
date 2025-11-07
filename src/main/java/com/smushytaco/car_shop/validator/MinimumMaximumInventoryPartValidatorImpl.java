package com.smushytaco.car_shop.validator;

import com.smushytaco.car_shop.domain.Part;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public final class MinimumMaximumInventoryPartValidatorImpl implements ConstraintValidator<MinimumMaximumInventoryPartValidator, Part> {
    @Override
    public void initialize(final MinimumMaximumInventoryPartValidator constraintAnnotation) { ConstraintValidator.super.initialize(constraintAnnotation); }
    @Override
    public boolean isValid(final Part part, final ConstraintValidatorContext constraintValidatorContext) { return part.getMaxInv() > part.getMinInv(); }
}