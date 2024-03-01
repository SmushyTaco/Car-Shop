package com.smushytaco.car_shop.validator;
import com.smushytaco.car_shop.domain.Part;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
@Component
public class MaximumInventoryPartValidatorImpl implements ConstraintValidator<MaximumInventoryPartValidator, Part> {
    @Override
    public void initialize(final MaximumInventoryPartValidator constraintAnnotation) { ConstraintValidator.super.initialize(constraintAnnotation); }
    @Override
    public boolean isValid(final Part part, final ConstraintValidatorContext constraintValidatorContext) { return part.getInv() <= part.getMaxInv(); }
}