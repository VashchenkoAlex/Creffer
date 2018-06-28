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
    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView pubDashboardGet(){
        return new ModelAndView("forward:/pages/publisher/dashboard.html");
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public ModelAndView pubDashboardPost(){
        return new ModelAndView("forward:/pages/publisher/dashboard.html");
    }
}
