package com.creffer.controllers.manager.offers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddOfferController {
    private static final Logger log = LoggerFactory.getLogger(AddOfferController.class);

    @GetMapping(value = "/offers/createOffer")
    public ModelAndView createOffer(){
        log.info("creating offer");
        return new ModelAndView("/protected/manager/offers/add_offer");
    }
}
