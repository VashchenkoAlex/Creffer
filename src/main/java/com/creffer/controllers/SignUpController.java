package com.creffer.controllers;

import com.creffer.models.users.UserModel;
import com.creffer.services.users.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView signUp(){
        return new ModelAndView("/signup");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView addNewPub(@ModelAttribute UserModel user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        UserModel userExists = userService.findUserByEmail(user.getEmail());
        if(userExists!=null){
            modelAndView.setViewName("/signup");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("/signup");
        }else {
            System.out.println("SignUpController-Post-BeforeSave");
            userService.savePublisher(user);
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }
}
