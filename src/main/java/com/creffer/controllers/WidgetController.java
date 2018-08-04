package com.creffer.controllers;

import com.creffer.models.users.UserModel;
import com.creffer.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Станислав on 21.07.2018.
 */


@Controller
public class WidgetController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/widget")
    public ModelAndView dashGet(){



        return new ModelAndView("/protected/widget");
    }
//    @PostMapping(value = "/widget")
//    public ModelAndView dashPost(HttpServletRequest request, HttpServletResponse response){
//        HttpSession session = request.getSession(true);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//        response.setStatus(HttpServletResponse.SC_OK);
//        return new ModelAndView("forward:/protected/widget");
//    }
    @PostMapping(value = "/widget")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ModelAndView mav = new ModelAndView();
    mav.addObject("json",userService.findUserByEmail("Staspropisnov@yandex.ru"));
      //  mav.setViewName("/protected/widget");

    return mav;

    }
    @RequestMapping(value = "/widget/find", method = RequestMethod.POST)
    public @ResponseBody   UserModel find() {


        return userService.findUserByEmail("Staspropisnov@yandex.ru");
    }
}