package com.smushytaco.car_shop.validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = {MinimumMaximumInventoryPartValidatorImpl.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinimumMaximumInventoryPartValidator {
    String message() default "The maximum inventory must be greater than the minimum inventory.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}