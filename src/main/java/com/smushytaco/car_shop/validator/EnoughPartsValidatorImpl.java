package com.smushytaco.car_shop.validator;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.service.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class EnoughPartsValidatorImpl implements ConstraintValidator<EnoughPartsValidator, Product> {
    private final ProductService productService;
    public EnoughPartsValidatorImpl() { this.productService = null; }
    @Autowired
    public EnoughPartsValidatorImpl(final ProductService productService) { this.productService = productService; }
    @Override
    public void initialize(final EnoughPartsValidator constraintAnnotation) { ConstraintValidator.super.initialize(constraintAnnotation); }
    @Override
    public boolean isValid(final Product product, final ConstraintValidatorContext constraintValidatorContext) {
        if (productService == null || product.getId() == 0) return true;
        Product existingProduct = productService.findById(product.getId());
        return productService.getParts(existingProduct.getId()).stream().noneMatch(part -> part.getMinInv() < (product.getInv() - existingProduct.getInv()));
    }
}