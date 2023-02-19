package exam.reseller.services.offer;

import exam.reseller.domain.entities.Offer;
import exam.reseller.domain.entities.User;
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
import java.util.NoSuchElementException;

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

        User currentUser = this.userRepository.findById(this.loggedUser.getId()).orElseThrow();
        Offer offer = this.offerRepository.saveAndFlush(Offer.builder()
                .price(offerAddModel.getPrice())
                .description(offerAddModel.getDescription())
                .condition(this.conditionRepository.findByName(offerAddModel.getCondition()))
                .seller(this.userRepository.findById(currentUser.getId()).orElseThrow())
                .build());

        currentUser.getOffers().add(offer);

    }


    @Override
    public List<OfferViewModel> getAllNonLoggedUserOffers() {

        return this.offerRepository.findAllBySeller_IdNot(this.loggedUser.getId()).orElseThrow(NoSuchElementException::new).stream()
                .map(this::buildViewModel).toList();
    }


    @Override
    @Transactional
    public void deleteOfferById(Long id) {
        this.offerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void buyOfferById(Long id) {
        Offer offer = this.offerRepository.findById(id).orElseThrow();
        User buyer = this.userRepository.findById(this.loggedUser.getId()).orElseThrow();
        User seller = offer.getSeller();

        offer.setBuyer(buyer);
        offer.setSeller(null);
        buyer.getBoughtOffers().add(offer);
        seller.getOffers().remove(offer);


    }


    private OfferViewModel buildViewModel(Offer offer) {
        return OfferViewModel.builder()
                .id(offer.getId())
                .username(offer.getSeller().getUsername())
                .description(offer.getDescription())
                .condition(offer.getCondition().getName().getLabel())
                .price(offer.getPrice())
                .build();
    }

}
