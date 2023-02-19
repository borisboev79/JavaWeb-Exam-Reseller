package exam.reseller.util.validations.checkUsernameAndEmailExistence;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldExistenceValidator.class)
public @interface ValidateUniqueFields {

    String message() default "User with this username or email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

