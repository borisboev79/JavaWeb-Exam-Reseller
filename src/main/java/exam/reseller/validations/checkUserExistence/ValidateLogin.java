package exam.reseller.validations.checkUserExistence;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = LoginValidator.class)
public @interface ValidateLogin {

    String message() default "User already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

