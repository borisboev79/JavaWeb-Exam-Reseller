package exam.reseller.controller;

import exam.reseller.domain.helpers.LoggedUser;
import exam.reseller.domain.models.OfferViewModel;
import exam.reseller.services.offer.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final OfferService offerService;


    @Autowired
    public HomeController(LoggedUser loggedUser, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;

    }

    @GetMapping
    public String getIndex() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView model) {

        List<OfferViewModel> loggedUserOffers = this.offerService.getAllLoggedUserOffers();
        List<OfferViewModel> otherOffers = this.offerService.getAllNonLoggedUserOffers();

        model.setViewName("home");
        // model.addObject("products", products);
        model.addObject("loggedUserId", this.loggedUser.getId());

        return model;
    }

    @GetMapping("/buy/{id}")
    public String buyOffer(@PathVariable Long id) {
        this.offerService.deleteOfferById(id);

        return "redirect:/home";
    }

    @GetMapping("/buy/all/{id}")
    public String buyAllProducts(@PathVariable Long id) {
        this.offerService.deleteAllOffersByUser(id);

        return "redirect:/home";
    }
}
