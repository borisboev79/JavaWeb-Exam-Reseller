package exam.reseller.validations.passwordMatcher;

import exam.reseller.domain.models.binding.UserRegisterModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterModel> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterModel userRegisterModel, ConstraintValidatorContext context) {

        return userRegisterModel.getPassword() != null && userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword());

    }
}
