package exam.reseller.services.user;


import exam.reseller.domain.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
