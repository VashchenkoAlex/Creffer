package com.creffer.controllers.not_protected;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manager/dashboard")
public class ManagerDashboardNotProtectedController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView dashboardGet(){
        return new ModelAndView("/manager/dashboard");
    }
}
