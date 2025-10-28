package com.smushytaco.car_shop.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {MaximumInventoryPartValidatorImpl.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaximumInventoryPartValidator {
    String message() default "The inventory must be less than or equal to the maximum inventory.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}