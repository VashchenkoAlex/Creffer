package com.creffer.controllers;

import com.creffer.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Станислав on 21.07.2018.
 */


@Controller
public class WidgetController {

    @Autowired
    private UserService userService;

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
    public @ResponseBody
    Map<String, Object> find() {

        Map<String, Object> map= new HashMap<>();

        map.put("contactList", userService.findUserByEmail("Staspropisnov@yandex.ru"));

        return map;
    }

 @RequestMapping(value = "/widget/userlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> userlist() {
        return new ResponseEntity<List>(userService.userlist(), HttpStatus.OK);
    }
    @RequestMapping(value = "/widget/remove_user", method = RequestMethod.POST)
    public int remove_user(@RequestParam("data") int data) {

        return userService.remove_user(data);
    }
}