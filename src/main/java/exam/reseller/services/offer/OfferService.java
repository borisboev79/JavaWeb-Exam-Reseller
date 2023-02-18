package exam.reseller.services.offer;

import exam.reseller.domain.enums.ConditionType;
import exam.reseller.domain.models.OfferAddModel;
import exam.reseller.domain.models.OfferViewModel;

import java.util.List;

public interface OfferService {

    void addOffer(OfferAddModel offerAddModel);

    List<OfferViewModel> getAllLoggedUserOffers();
    List<OfferViewModel> getAllNonLoggedUserOffers();

    void deleteOfferById(Long id);

    void buyOfferById(Long id);

}
