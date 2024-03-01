package com.smushytaco.car_shop.validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = {PriceProductValidatorImpl.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceProductValidator {
    String message() default "The price of the product must be greater than or equal to the sum of the price of the parts.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}