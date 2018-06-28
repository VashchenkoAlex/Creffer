package com.creffer.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
@RequestMapping(value = "/adminDashboard")
public class DashBoardController {
    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView dashGet(){
        ModelAndView mav = new ModelAndView();
        mav.setView(new MappingJackson2JsonView());
        return new ModelAndView("forward:/pages/admin/dashboard.html");
    }
    @RequestMapping(method = RequestMethod.POST,produces = "text/html")
    public ModelAndView dashPost(){
        return new ModelAndView("forward:/pages/admin/dashboard.html");
    }
}
