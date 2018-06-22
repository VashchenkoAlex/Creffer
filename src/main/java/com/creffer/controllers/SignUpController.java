package com.creffer.controllers;

import com.creffer.models.users.UserModel;
import com.creffer.services.users.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Configuration
public class SignUpController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserModel user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        UserModel userExists = userService.findUserByEmail(user.getEmail());
        if (userExists!=null){
            bindingResult
                    .rejectValue("email","error.admins","There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("signup");
        }else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage","User has been registered successfully");
            modelAndView.addObject("user", new UserModel());
            modelAndView.setViewName("signup");
        }
        return modelAndView;
    }
}
