package com.smushytaco.car_shop.validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = {DeleteOrUpdatePartValidatorImpl.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteOrUpdatePartValidator {
    String message() default "Part cannot be deleted or updated if used in a product.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}