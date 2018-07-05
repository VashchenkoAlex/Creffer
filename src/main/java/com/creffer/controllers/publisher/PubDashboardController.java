package com.creffer.controllers.publisher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/publisherDashboard")
public class PubDashboardController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView pubDashboardGet(){
        System.out.println("/publisherDashboard");
        return new ModelAndView("/protected/publisher/dashboard");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView pubDashboardPost(){
        return new ModelAndView("forward:/pages/protected/publisher/dashboard");
    }
}
