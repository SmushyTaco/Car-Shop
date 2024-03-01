package com.smushytaco.car_shop.validator;
import com.smushytaco.car_shop.domain.Part;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
@Component
public class MinimumInventoryPartValidatorImpl implements ConstraintValidator<MinimumInventoryPartValidator, Part> {
    @Override
    public void initialize(final MinimumInventoryPartValidator constraintAnnotation) { ConstraintValidator.super.initialize(constraintAnnotation); }
    @Override
    public boolean isValid(final Part part, final ConstraintValidatorContext constraintValidatorContext) { return part.getInv() >= part.getMinInv(); }
}