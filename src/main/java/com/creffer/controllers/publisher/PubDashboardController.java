package com.creffer.controllers.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PubDashboardController {
    private static final Logger log = LoggerFactory.getLogger(PubDashboardController.class);
    @GetMapping(value = "/publisherDashboard")
    public ModelAndView pubDashboardGet(){
        log.info("/publisherDashboard");
        return new ModelAndView("/protected/publisher/dashboard");
    }

    @PostMapping(value = "/publisherDashboard")
    public ModelAndView pubDashboardPost(){
        return new ModelAndView("redirect:/pages/protected/publisher/dashboard");
    }
}
