package com.creffer.controllers;

import com.creffer.models.users.UserModel;
import com.creffer.services.users.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public ModelAndView signUp(){
        return new ModelAndView("/signup");
    }

    @RequestMapping(method = RequestMethod.POST,produces = "text/html")
    public ModelAndView addNewPub(@ModelAttribute UserModel user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        UserModel userExists = userService.findUserByEmail(user.getEmail());
        if(userExists!=null){
            modelAndView.setViewName("forward:/signup");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("forward:/signup");
        }else {
            userService.savePublisher(user);
            modelAndView.setViewName("forward:/publisherDashboard");
        }
        return modelAndView;
    }
}
