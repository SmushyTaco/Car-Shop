package com.smushytaco.car_shop.validator;

import com.smushytaco.car_shop.domain.Part;
import com.smushytaco.car_shop.service.PartService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class DeleteOrUpdatePartValidatorImpl implements ConstraintValidator<DeleteOrUpdatePartValidator, Part> {
    private final PartService partService;
    public DeleteOrUpdatePartValidatorImpl() { this.partService = null; }
    @Autowired
    public DeleteOrUpdatePartValidatorImpl(final PartService partService) { this.partService = partService; }
    @Override
    public void initialize(final DeleteOrUpdatePartValidator constraintAnnotation) { ConstraintValidator.super.initialize(constraintAnnotation); }
    @Override
    public boolean isValid(final Part part, final ConstraintValidatorContext constraintValidatorContext) { return partService == null || partService.getProducts(part.id).isEmpty(); }
}