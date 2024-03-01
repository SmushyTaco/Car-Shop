package com.smushytaco.car_shop.validator;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.service.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class PriceProductValidatorImpl implements ConstraintValidator<PriceProductValidator, Product> {
    private final ProductService productService;
    public PriceProductValidatorImpl() { this.productService = null; }
    @Autowired
    public PriceProductValidatorImpl(final ProductService productService) { this.productService = productService; }
    @Override
    public void initialize(final PriceProductValidator constraintAnnotation) { ConstraintValidator.super.initialize(constraintAnnotation); }
    @Override
    public boolean isValid(final Product product, final ConstraintValidatorContext constraintValidatorContext) { return productService == null || product.getId() == 0 || productService.productPriceIsValid(product, null); }
}