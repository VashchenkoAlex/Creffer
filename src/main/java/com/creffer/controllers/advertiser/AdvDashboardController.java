package com.creffer.controllers.advertiser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/advertiserDashboard")
public class AdvDashboardController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView pubDashboardGet(){
        System.out.println("/advertiserDashboard");
        return new ModelAndView("/pages/protected/advertiser/dashboard");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView pubDashboardPost(){
        return new ModelAndView("forward:/pages/protected/publisher/dashboard");
    }
}
