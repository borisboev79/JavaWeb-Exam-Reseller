package exam.reseller.util.validations.checkUsernameAndEmailExistence;

import exam.reseller.domain.entities.User;
import exam.reseller.domain.models.binding.UserRegisterModel;
import exam.reseller.services.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FieldExistenceValidator implements ConstraintValidator<ValidateUniqueFields, UserRegisterModel> {

    private final UserService userService;

    @Autowired
    public FieldExistenceValidator(UserService userService) {
        this.userService = userService;

    }


    @Override
    public void initialize(ValidateUniqueFields constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterModel userRegisterModel, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optionalUser = userService.findByUsername(userRegisterModel.getUsername());

        if (optionalUser.isPresent()){
            return false;
        }

        Optional<User> optionalUserEmailCheck = userService.findByEmail(userRegisterModel.getEmail());

        return optionalUserEmailCheck.isEmpty();

    }
}
