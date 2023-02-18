package exam.reseller.services.user;

import exam.reseller.domain.models.binding.UserLoginModel;
import exam.reseller.domain.models.binding.UserRegisterModel;

public interface AuthService {

    default void registerUser(UserRegisterModel userRegisterModel) {
    }

    default void loginUser(UserLoginModel userLoginModel) {
    }

    default void logoutUser() {
    }

    boolean isAuthentic(String username, String password);
}
