package exam.reseller.controller;

import exam.reseller.domain.models.OfferAddModel;
import exam.reseller.services.offer.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;

    }

    @GetMapping("/add")
    public String getAddProduct(){
        return "offer-add";
    }

    @PostMapping("/add")
        public String addProduct(@Valid @ModelAttribute(name= "offerAddModel") OfferAddModel offerAddModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("offerAddModel", offerAddModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddModel", bindingResult);

            return "redirect:add";
        }
        this.offerService.addOffer(offerAddModel);

        return "redirect:/home";
    }

    @ModelAttribute(name="offerAddModel")
    public OfferAddModel productAddModel(){
        return new OfferAddModel();
    }
}
