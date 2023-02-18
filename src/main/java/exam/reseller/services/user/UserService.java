package exam.reseller.services.user;


import exam.reseller.domain.entities.Offer;
import exam.reseller.domain.entities.User;
import exam.reseller.domain.models.OfferViewModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    public List<OfferViewModel> allBought();

    public List<OfferViewModel> allOwn();
}
