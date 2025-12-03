package com.smushytaco.car_shop.validator;

import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.service.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class EnoughPartsValidatorImpl implements ConstraintValidator<EnoughPartsValidator, Product> {
    private final ProductService productService;
    public EnoughPartsValidatorImpl() { this.productService = null; }
    @Autowired
    public EnoughPartsValidatorImpl(final ProductService productService) { this.productService = productService; }
    @Override
    public void initialize(final EnoughPartsValidator constraintAnnotation) { ConstraintValidator.super.initialize(constraintAnnotation); }
    @Override
    public boolean isValid(final Product product, final ConstraintValidatorContext constraintValidatorContext) {
        if (productService == null || product.id == 0) return true;
        final Product existingProduct = productService.findById(product.id);
        return productService.getParts(existingProduct.id).stream().noneMatch(part -> part.minInv < (product.inv - existingProduct.inv));
    }
}