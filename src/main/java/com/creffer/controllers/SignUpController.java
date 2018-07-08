package com.creffer.controllers;

import com.creffer.models.users.UserModel;
import com.creffer.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {
    private static final Logger log = LoggerFactory.getLogger(SignUpController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public ModelAndView signUp(){
        return new ModelAndView("/signup");
    }

    @PostMapping("/signup")
    @ResponseBody
    public ModelAndView addNewPub(@ModelAttribute UserModel user, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView();
        UserModel userExists = userService.findUserByEmail(user.getEmail());
        if(userExists!=null){
            mav.addObject("error","User already exists");
            mav.setViewName("redirect:/signup");
            return mav;
        }
        if (bindingResult.hasErrors()){
            mav.addObject("error","Some error");
            mav.setViewName("redirect:/signup");
            return mav;
        }
        log.info("SignUpController-Post-BeforeSave");
        userService.savePublisher(user);
        mav.setViewName("redirect:/login");
        return mav;
    }
    @GetMapping(value = "/signup",params = {"error"})
    @ResponseBody
    public ModelAndView signUpWithError(@RequestParam("error") String error){
        log.info("Sign up with error"+error);
        ModelAndView mav = new ModelAndView("redirect:/signup");
        mav.addObject("error",error);
        return mav;
    }
}
