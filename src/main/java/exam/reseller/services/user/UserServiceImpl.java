package exam.reseller.services.user;

import exam.reseller.domain.entities.Offer;
import exam.reseller.domain.entities.User;
import exam.reseller.domain.helpers.LoggedUser;
import exam.reseller.domain.models.OfferViewModel;
import exam.reseller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }



    public List<OfferViewModel> allBought(){
        User user = this.userRepository.findById(this.loggedUser.getId()).get();
        return user.getBoughtOffers().stream().map(offer -> OfferViewModel.builder()
                .id(offer.getId())
                .description(offer.getDescription())
                .condition(offer.getCondition().getName().getLabel())
                .price(offer.getPrice())
                .username(offer.getUser().getUsername())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<OfferViewModel> allOwn() {
        User user = this.userRepository.findById(this.loggedUser.getId()).get();
        return user.getOffers().stream().map(offer -> OfferViewModel.builder()
                .id(offer.getId())
                .description(offer.getDescription())
                .condition(offer.getCondition().getName().getLabel())
                .price(offer.getPrice())
                .username(offer.getUser().getUsername())
                .build()).collect(Collectors.toList());
    }


}
