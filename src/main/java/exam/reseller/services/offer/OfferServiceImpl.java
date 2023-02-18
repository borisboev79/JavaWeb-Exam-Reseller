package exam.reseller.services.offer;

import exam.reseller.domain.entities.Offer;
import exam.reseller.domain.entities.User;
import exam.reseller.domain.enums.ConditionType;
import exam.reseller.domain.helpers.LoggedUser;
import exam.reseller.domain.models.OfferAddModel;
import exam.reseller.domain.models.OfferViewModel;
import exam.reseller.repositories.ConditionRepository;
import exam.reseller.repositories.OfferRepository;
import exam.reseller.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ConditionRepository conditionRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ConditionRepository conditionRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }


    @Override
    public void addOffer(OfferAddModel offerAddModel) {

        User currentUser = this.userRepository.findById(this.loggedUser.getId()).get();
        Offer offer = this.offerRepository.saveAndFlush(Offer.builder()
                .price(offerAddModel.getPrice())
                .description(offerAddModel.getDescription())
                .condition(this.conditionRepository.findByName(offerAddModel.getCondition()))
                .user(this.userRepository.findById(currentUser.getId()).get())
                .build());

        currentUser.getOffers().add(offer);

    }

    @Override
    public List<OfferViewModel> getAllLoggedUserOffers() {
        return this.offerRepository.findAllByUser_Id(this.loggedUser.getId()).orElse(null).stream()
                .map(this::viewModel).toList();
    }

    @Override
    public List<OfferViewModel> getAllNonLoggedUserOffers() {

        return this.offerRepository.findAllByUser_IdNot(this.loggedUser.getId()).orElse(null).stream()
                .map(this::viewModel).toList();
    }




    @Override
    @Transactional
    public void deleteOfferById(Long id) {
        this.offerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void buyOfferById(Long id) {
        Offer offer = this.offerRepository.findById(id).get();
        User buyer = this.userRepository.findById(this.loggedUser.getId()).get();
        User seller = offer.getUser();

        offer.setUser(buyer);
        buyer.getBoughtOffers().add(offer);
        seller.getOffers().remove(offer);


    }


    private OfferViewModel viewModel(Offer offer) {
        return OfferViewModel.builder()
                .id(offer.getId())
                .username(offer.getUser().getUsername())
                .description(offer.getDescription())
                .condition(offer.getCondition().getName().getLabel())
                .price(offer.getPrice())
                .build();
    }

}
